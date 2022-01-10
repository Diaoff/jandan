package com.ruoyi.project.jandan.images.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 图片对象 images
 *
 * @author diaoff
 * @date 2021-07-27
 */
public class Images extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 链接
	 */
	@Excel(name = "链接")
	private String url;

	/**
	 * 完整链接
	 */
	@Excel(name = "完整链接")
	private String fullUrl;

	/**
	 * 域名
	 */
	@Excel(name = "域名")
	private String host;

	/**
	 * thumb size
	 */
	@Excel(name = "thumb size")
	private String thumbSize;

	/**
	 * 拓展名
	 */
	@Excel(name = "拓展名")
	private String ext;

	/**
	 * 文件名
	 */
	@Excel(name = "文件名")
	private String fileName;

	/**
	 * 图片归属ID
	 */
	@Excel(name = "图片归属ID")
	private String fdModelId;

	/**
	 * 图片归属域
	 */
	@Excel(name = "图片归属域")
	private String fdModelName;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFullUrl() {
		return fullUrl;
	}

	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getThumbSize() {
		return thumbSize;
	}

	public void setThumbSize(String thumbSize) {
		this.thumbSize = thumbSize;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFdModelId() {
		return fdModelId;
	}

	public void setFdModelId(String fdModelId) {
		this.fdModelId = fdModelId;
	}

	public String getFdModelName() {
		return fdModelName;
	}

	public void setFdModelName(String fdModelName) {
		this.fdModelName = fdModelName;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("url", getUrl())
				.append("fullUrl", getFullUrl())
				.append("host", getHost())
				.append("thumbSize", getThumbSize())
				.append("ext", getExt())
				.append("fileName", getFileName())
				.append("fdModelId", getFdModelId())
				.append("fdModelName", getFdModelName())
				.toString();
	}
}
