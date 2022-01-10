package com.ruoyi.project.jandan.images.mapper;

import com.ruoyi.project.jandan.images.domain.Images;

import java.util.List;

/**
 * 图片Mapper接口
 *
 * @author diaoff
 * @date 2021-07-27
 */
public interface ImagesMapper {
	/**
	 * 查询图片
	 *
	 * @param url 图片ID
	 * @return 图片
	 */
	Images selectImagesById(String url);

	/**
	 * 查询图片列表
	 *
	 * @param images 图片
	 * @return 图片集合
	 */
	List<Images> selectImagesList(Images images);

	/**
	 * 新增图片
	 *
	 * @param images 图片
	 * @return 结果
	 */
	int insertImages(Images images);

	/**
	 * 修改图片
	 *
	 * @param images 图片
	 * @return 结果
	 */
	int updateImages(Images images);

	/**
	 * 删除图片
	 *
	 * @param url 图片ID
	 * @return 结果
	 */
	int deleteImagesById(String url);

	/**
	 * 批量删除图片
	 *
	 * @param urls 需要删除的数据ID
	 * @return 结果
	 */
	int deleteImagesByIds(String[] urls);

	Images selectImagesByModelId(String fdModelId);
}
