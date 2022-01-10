package com.ruoyi.project.jandan.job;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.project.jandan.comment.domain.TComment;
import com.ruoyi.project.jandan.comment.service.ITCommentService;
import com.ruoyi.project.jandan.images.domain.Images;
import com.ruoyi.project.jandan.images.service.IImagesService;
import com.ruoyi.project.system.config.domain.Config;
import com.ruoyi.project.system.config.service.IConfigService;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.selector.Json;

import java.util.List;

/**
 * @author diaoff
 * @version 1.0 2021-07-27
 * @date: 2021-07-27 17:52
 */
public class JandanCommentPipeline implements Pipeline {

	String configKey;

	ITCommentService commentService;

	IImagesService imagesService;

	IConfigService configService;

	public JandanCommentPipeline(String configKey, ITCommentService commentService, IConfigService configService, IImagesService imagesService) {
		this.configKey = configKey;
		this.commentService = commentService;
		this.configService = configService;
		this.imagesService = imagesService;
	}

	public String getConfigKey() {
		return configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}

	public ITCommentService getCommentService() {
		return commentService;
	}

	public void setCommentService(ITCommentService commentService) {
		this.commentService = commentService;
	}

	public IConfigService getConfigService() {
		return configService;
	}

	public void setConfigService(IConfigService configService) {
		this.configService = configService;
	}

	@Override
	public void process(ResultItems resultItems, Task task) {
		Json json = resultItems.get("json");
		Config config = new Config();
		config.setConfigKey(configKey);
		List<Config> configs = configService.selectConfigList(config);
		if (!configs.isEmpty()) {
			config = configs.get(0);
		}
		JSONObject data = json.toObject(JSONObject.class);
		JSONArray comments = data.getJSONArray("data");
		if (comments.size() == 0) {
			if (config != null) {
				config.setConfigValue("9999999");
				configService.updateConfig(config);
			}
			return;
		}
		List<TComment> tComments = comments.toJavaList(TComment.class);
		String lastId = config != null ? config.getConfigValue() : "9999999";
		for (TComment tComment : tComments) {
			try {
				Long id = tComment.getId();
				TComment tComment1 = commentService.selectTCommentById(id);
				if (tComment1 == null) {
					int i = commentService.insertTComment(tComment);
				} else {
					commentService.updateTComment(tComment);
				}
				List<Images> images = tComment.getImages();
				for (Images image : images) {
					try {
						List<Images> imgs = imagesService.selectImagesList(image);
						if (imgs.isEmpty()) {
							image.setFdModelId(String.valueOf(tComment.getId()));
							image.setFdModelName(TComment.class.getName());
							imagesService.insertImages(image);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				lastId = String.valueOf(id);
				if (config != null) {
					config.setConfigValue(lastId);
					configService.updateConfig(config);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
