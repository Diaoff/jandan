package com.ruoyi.project.jandan.comment.mapper;

import com.ruoyi.project.jandan.comment.domain.TComment;

import java.util.List;

/**
 * 帖子Mapper接口
 *
 * @author diaoff
 * @date 2021-07-27
 */
public interface TCommentMapper {
	/**
	 * 查询帖子
	 *
	 * @param id 帖子ID
	 * @return 帖子
	 */
    TComment selectTCommentById(Long id);

	/**
	 * 查询帖子加图片
	 *
	 * @param id
	 * @return
	 */
    TComment selectTCommentImagesById(Long id);

	/**
	 * 查询帖子列表
	 *
	 * @param tComment 帖子
	 * @return 帖子集合
	 */
    List<TComment> selectTCommentList(TComment tComment);

	List<TComment> selectTCommentImagesList(TComment tComment);

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
	 * 删除帖子
	 *
	 * @param id 帖子ID
	 * @return 结果
	 */
    int deleteTCommentById(Long id);

	/**
	 * 批量删除帖子
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
    int deleteTCommentByIds(String[] ids);
}
