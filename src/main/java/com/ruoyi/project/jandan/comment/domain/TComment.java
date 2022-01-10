package com.ruoyi.project.jandan.comment.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.project.jandan.images.domain.Images;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 帖子对象 t_comment
 *
 * @author diaoff
 * @date 2021-07-27
 */
public class TComment extends BaseEntity {
	private static final long serialVersionUID = 1L;
	List<Images> images = new ArrayList();
	/**
	 * ID
	 */
	private Long id;
	/**
	 * 分区ID
	 */
	@Excel(name = "分区ID")
	private Long postId;
	/**
	 * 作者
	 */
	@Excel(name = "作者")
	private String author;
	/**
	 * 作者类型
	 */
	@Excel(name = "作者类型")
	private Long authorType;
	/**
	 * 时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Excel(name = "时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
	private Date date;
	/**
	 * 时间戳(秒)
	 */
	@Excel(name = "时间戳(秒)")
	private Long dateUnix;
	/**
	 * 分区名
	 */
	@Excel(name = "分区名")
	private String postTitle;
	/**
	 * 内容
	 */
	@Excel(name = "内容")
	private String content;
	/**
	 * 用户ID
	 */
	@Excel(name = "用户ID")
	private Long userId;
	/**
	 * 赞
	 */
	@Excel(name = "赞")
	private Long votePositive;
	/**
	 * 踩
	 */
	@Excel(name = "踩")
	private Long voteNegative;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Long getAuthorType() {
		return authorType;
	}

	public void setAuthorType(Long authorType) {
		this.authorType = authorType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getDateUnix() {
		return dateUnix;
	}

	public void setDateUnix(Long dateUnix) {
		this.dateUnix = dateUnix;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getVotePositive() {
		return votePositive;
	}

	public void setVotePositive(Long votePositive) {
		this.votePositive = votePositive;
	}

	public Long getVoteNegative() {
		return voteNegative;
	}

	public void setVoteNegative(Long voteNegative) {
		this.voteNegative = voteNegative;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("id", getId())
				.append("postId", getPostId())
				.append("author", getAuthor())
				.append("authorType", getAuthorType())
				.append("date", getDate())
				.append("dateUnix", getDateUnix())
				.append("postTitle", getPostTitle())
				.append("content", getContent())
				.append("userId", getUserId())
				.append("votePositive", getVotePositive())
				.append("voteNegative", getVoteNegative())
				.toString();
	}

	public List<Images> getImages() {
		return images;
	}

	public void setImages(List<Images> images) {
		this.images = images;
	}
}
