package com.ruoyi.project.jandan.job;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.jandan.comment.service.ITCommentService;
import com.ruoyi.project.jandan.images.service.IImagesService;
import com.ruoyi.project.system.config.service.IConfigService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.text.MessageFormat;

/**
 * @author diaoff jandanCommentTask.pullComments('girl.start.id');
 * @version 1.0 2021-07-27
 * @date: 2021-07-27 16:03
 */
//jandanCommentTask.pullComments(108629,'girl.start.id');
//jandanCommentTask.pullComments(21183,'sui.start.id');
//jandanCommentTask.pullComments(26402,'tu.start.id');
@Component("jandanCommentTask")
public class JandanCommentTask implements PageProcessor {

	public static final String COMMENT_LIST = "https://api.jandan.net/api/v1/comment/list/{0}";
	private static final Log logger = LogFactory
			.getLog(JandanCommentTask.class);
	@Autowired
	ITCommentService commentService;
	@Autowired
	IConfigService configService;
	@Autowired
	IImagesService imagesService;
	private Site site = Site.me().setDomain("api.jandan.net");

	/**
	 * 爬取分区下的帖子
	 *
	 * @param postId
	 */
	public void pullComments(Integer postId, String configKey) {
		String startId = configService.selectConfigByKey(configKey);
		logger.error(StringUtils.format("执行分区爬取： 分区ID{}", postId));
		String url = MessageFormat.format(COMMENT_LIST, String.valueOf(postId));
		try {
			URIBuilder uri = new URIBuilder(url).addParameter("start_id", startId);
			url = uri.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Spider.create(this).addUrl(url)
				.addPipeline(new JandanCommentPipeline(configKey, commentService, configService, imagesService)).run();
	}

	@Override
	public void process(Page page) {
		page.putField("json", page.getJson());
	}

	@Override
	public Site getSite() {
		return site;
	}
}
