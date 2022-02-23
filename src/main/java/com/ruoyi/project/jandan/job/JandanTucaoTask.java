package com.ruoyi.project.jandan.job;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.jandan.comment.domain.TComment;
import com.ruoyi.project.jandan.comment.service.ITCommentService;
import com.ruoyi.project.jandan.tucao.domain.TTucao;
import com.ruoyi.project.jandan.tucao.service.ITTucaoService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author diaoff
 * @version 1.0 2021-07-27
 * @date: 2021-07-27 16:03
 */
@SuppressWarnings("all")
@Component("jandanTucaoTask")
public class JandanTucaoTask implements PageProcessor, InitializingBean , DisposableBean {

	public static final String COMMENT_LIST = "https://api.jandan.net/api/v1/tucao/list/{0}";
	private static final Log logger = LogFactory
			.getLog(JandanTucaoTask.class);
	@Autowired
	ITCommentService commentService;
	@Autowired
	ITTucaoService tucaoService;

	ExecutorService executor = null;

	private Site site = Site.me().setDomain("api.jandan.net");

	public void handlePull(Integer day) throws InterruptedException {
		TComment tComment = new TComment();
		Calendar time = Calendar.getInstance();
		time.add(Calendar.DAY_OF_MONTH,day * -1);
		tComment.setDate(time.getTime());
		List<Long> ids = commentService.selectTCommentIds(tComment);
		CountDownLatch cdl = new CountDownLatch(ids.size());
		//pullComments(ids.get(0));
		ids.forEach(id->{
			executor.submit(new Task(cdl, id));
		});
		cdl.await();
		System.out.println("拉取"+ids.size() +"条评论完毕");
	}

	/**
	 * 爬取分区下的帖子
	 *
	 * @param commentId
	 */
	public void pullComments(Long commentId) {
		logger.debug(StringUtils.format("执行评论爬取： 帖子ID{}", commentId));
		String url = MessageFormat.format(COMMENT_LIST, String.valueOf(commentId));
		Spider.create(this).addUrl(url)
				.addPipeline(new ConsolePipeline()).run();
	}

	@Override
	public void process(Page page) {
		Long commentId = null;
		String url = page.getRequest().getUrl();
		if(StringUtils.isNotEmpty(url)){
			try {
				commentId = Long.valueOf(url.substring(url.lastIndexOf("/")+1));
			} catch (Exception e) {
			    e.printStackTrace();
			}
		}
		Json json = page.getJson();
		JSONObject data = json.toObject(JSONObject.class);
		JSONArray comments = data.getJSONObject("data").getJSONArray("list");
		List<TTucao> tucaos = comments.toJavaList(TTucao.class);
		for (TTucao tucao : tucaos) {
			TTucao tucao1 = tucaoService.selectTTucaoById(tucao.getId());

			tucao.setCommentId(commentId);

			try {
				if (tucao1 == null){
					int i = tucaoService.insertTTucao(tucao);
				}else{
					tucao1.setCommentId(commentId);
					tucaoService.updateTTucao(tucao);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Site getSite() {
		return site;
	}



	@Override
	public void afterPropertiesSet() throws Exception {
		executor = Executors.newFixedThreadPool(10);
	}

	@Override
	public void destroy() throws Exception {
		executor.shutdown();
	}
	private class Task  implements Runnable{
		private CountDownLatch cdl;
		private Long id;

		public Task(CountDownLatch cdl, Long id) {
			this.cdl = cdl;
			this.id = id;
		}

		@Override
		public void run() {
			try {
				pullComments(id);
			} catch (Exception e) {
			    e.printStackTrace();
			}finally {
				cdl.countDown();
			}
		}
	}
}
