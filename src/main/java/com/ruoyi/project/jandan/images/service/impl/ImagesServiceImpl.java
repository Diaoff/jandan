package com.ruoyi.project.jandan.images.service.impl;

import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.jandan.images.domain.Images;
import com.ruoyi.project.jandan.images.mapper.ImagesMapper;
import com.ruoyi.project.jandan.images.service.IImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 图片Service业务层处理
 *
 * @author diaoff
 * @date 2021-07-27
 */
@Service
public class ImagesServiceImpl implements IImagesService {
	@Autowired
	private ImagesMapper imagesMapper;

	/**
	 * 查询图片
	 *
	 * @param url 图片ID
	 * @return 图片
	 */
	@Override
	public Images selectImagesById(String url) {
		return imagesMapper.selectImagesById(url);
	}

	/**
	 * 查询图片列表
	 *
	 * @param images 图片
	 * @return 图片
	 */
	@Override
	public List<Images> selectImagesList(Images images) {
		return imagesMapper.selectImagesList(images);
	}

	/**
	 * 新增图片
	 *
	 * @param images 图片
	 * @return 结果
	 */
	@Override
	public int insertImages(Images images) {
		return imagesMapper.insertImages(images);
	}

	/**
	 * 修改图片
	 *
	 * @param images 图片
	 * @return 结果
	 */
	@Override
	public int updateImages(Images images) {
		return imagesMapper.updateImages(images);
	}

	/**
	 * 删除图片对象
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteImagesByIds(String ids) {
		return imagesMapper.deleteImagesByIds(Convert.toStrArray(ids));
	}

	/**
	 * 删除图片信息
	 *
	 * @param url 图片ID
	 * @return 结果
	 */
	@Override
	public int deleteImagesById(String url) {
		return imagesMapper.deleteImagesById(url);
	}
}
