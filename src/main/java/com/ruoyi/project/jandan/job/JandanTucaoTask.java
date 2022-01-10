package com.ruoyi.project.jandan.job;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.jandan.comment.domain.TComment;
import com.ruoyi.project.jandan.comment.service.ITCommentService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;

import java.text.MessageFormat;
import java.util.List;

/**
 * @author diaoff
 * @version 1.0 2021-07-27
 * @date: 2021-07-27 16:03
 */
@SuppressWarnings("all")
@Component("jandanTucaoTask")
public class JandanTucaoTask implements PageProcessor {

	public static final String COMMENT_LIST = "https://api.jandan.net/api/v1/tucao/list/{0}";
	private static final Log logger = LogFactory
			.getLog(JandanTucaoTask.class);
	@Autowired
	ITCommentService commentService;
	private Site site = Site.me().setDomain("api.jandan.net");

	/**
	 * 爬取分区下的帖子
	 *
	 * @param commentId
	 */
	public void pullComments(Integer commentId) {
		logger.debug(StringUtils.format("执行评论爬取： 帖子ID{}", commentId));
		String url = MessageFormat.format(COMMENT_LIST, String.valueOf(commentId));
		Spider.create(this).addUrl(url)
				.addPipeline(new ConsolePipeline()).run();
	}

	@Override
	public void process(Page page) {
		Json json = page.getJson();
		JSONObject data = json.toObject(JSONObject.class);
		JSONArray comments = data.getJSONArray("data");
		List<TComment> tComments = comments.toJavaList(TComment.class);
		for (TComment tComment : tComments) {
			try {
				int i = commentService.insertTComment(tComment);
				System.out.println(i);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Site getSite() {
		return site;
	}
}
