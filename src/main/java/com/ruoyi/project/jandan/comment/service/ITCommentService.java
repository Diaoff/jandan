package com.ruoyi.project.jandan.comment.service;

import com.ruoyi.project.jandan.comment.domain.TComment;

import java.util.List;

/**
 * 帖子Service接口
 *
 * @author diaoff
 * @date 2021-07-27
 */
public interface ITCommentService {
	/**
	 * 查询帖子
	 *
	 * @param id 帖子ID
	 * @return 帖子
	 */
	TComment selectTCommentById(Long id);
	TComment selectTCommentImagesById(Long id);

	/**
	 * 查询帖子列表
	 *
	 * @param tComment 帖子
	 * @return 帖子集合
	 */
	List<TComment> selectTCommentList(TComment tComment);

	/**
	 * 新增帖子
	 *
	 * @param tComment 帖子
	 * @return 结果
	 */
	int insertTComment(TComment tComment);

	/**
	 * 修改帖子
	 *
	 * @param tComment 帖子
	 * @return 结果
	 */
	int updateTComment(TComment tComment);

	/**
	 * 批量删除帖子
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	int deleteTCommentByIds(String ids);

	/**
	 * 删除帖子信息
	 *
	 * @param id 帖子ID
	 * @return 结果
	 */
	int deleteTCommentById(Long id);

	/**
	 * 查询帖子列表
	 *
	 * @param tComment 帖子
	 * @return 帖子集合
	 */
	List<TComment> selectTCommentImagesList(TComment tComment);
}
