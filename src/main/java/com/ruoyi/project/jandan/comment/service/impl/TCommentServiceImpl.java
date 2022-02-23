package com.ruoyi.project.jandan.comment.service.impl;

import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.jandan.comment.domain.TComment;
import com.ruoyi.project.jandan.comment.mapper.TCommentMapper;
import com.ruoyi.project.jandan.comment.service.ITCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 帖子Service业务层处理
 *
 * @author diaoff
 * @date 2021-07-27
 */
@Service
public class TCommentServiceImpl implements ITCommentService {
	@Autowired
	private TCommentMapper tCommentMapper;

	/**
	 * 查询帖子
	 *
	 * @param id 帖子ID
	 * @return 帖子
	 */
	@Override
	public TComment selectTCommentById(Long id) {
		return tCommentMapper.selectTCommentById(id);
	}

	@Override
	public TComment selectTCommentImagesById(Long id) {
		return tCommentMapper.selectTCommentImagesById(id);
	}

	/**
	 * 查询帖子列表
	 *
	 * @param tComment 帖子
	 * @return 帖子
	 */
	@Override
	public List<TComment> selectTCommentList(TComment tComment) {
		return tCommentMapper.selectTCommentList(tComment);
	}

	/**
	 * 新增帖子
	 *
	 * @param tComment 帖子
	 * @return 结果
	 */
	@Override
	public int insertTComment(TComment tComment) {
		return tCommentMapper.insertTComment(tComment);
	}

	/**
	 * 修改帖子
	 *
	 * @param tComment 帖子
	 * @return 结果
	 */
	@Override
	public int updateTComment(TComment tComment) {
		return tCommentMapper.updateTComment(tComment);
	}

	/**
	 * 删除帖子对象
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteTCommentByIds(String ids) {
		return tCommentMapper.deleteTCommentByIds(Convert.toStrArray(ids));
	}

	/**
	 * 删除帖子信息
	 *
	 * @param id 帖子ID
	 * @return 结果
	 */
	@Override
	public int deleteTCommentById(Long id) {
		return tCommentMapper.deleteTCommentById(id);
	}

	/**
	 * 查询帖子列表
	 *
	 * @param tComment 帖子
	 * @return 帖子
	 */
	@Override
	public List<TComment> selectTCommentImagesList(TComment tComment) {
		return tCommentMapper.selectTCommentImagesList(tComment);
	}

	@Override
	public List<Long> selectTCommentIds(TComment tComment) {
		return tCommentMapper.selectTCommentIds(tComment);
	}
}
