package com.ruoyi.project.jandan.tucao.service.impl;

import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.jandan.tucao.domain.TTucao;
import com.ruoyi.project.jandan.tucao.mapper.TTucaoMapper;
import com.ruoyi.project.jandan.tucao.service.ITTucaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 吐槽Service业务层处理
 *
 * @author ruoyi
 * @date 2021-07-27
 */
@Service
public class TTucaoServiceImpl implements ITTucaoService {
	@Autowired
	private TTucaoMapper tTucaoMapper;

	/**
	 * 查询吐槽
	 *
	 * @param id 吐槽ID
	 * @return 吐槽
	 */
	@Override
	public TTucao selectTTucaoById(Long id) {
		return tTucaoMapper.selectTTucaoById(id);
	}

	/**
	 * 查询吐槽列表
	 *
	 * @param tTucao 吐槽
	 * @return 吐槽
	 */
	@Override
	public List<TTucao> selectTTucaoList(TTucao tTucao) {
		return tTucaoMapper.selectTTucaoList(tTucao);
	}

	/**
	 * 新增吐槽
	 *
	 * @param tTucao 吐槽
	 * @return 结果
	 */
	@Override
	public int insertTTucao(TTucao tTucao) {
		return tTucaoMapper.insertTTucao(tTucao);
	}

	/**
	 * 修改吐槽
	 *
	 * @param tTucao 吐槽
	 * @return 结果
	 */
	@Override
	public int updateTTucao(TTucao tTucao) {
		return tTucaoMapper.updateTTucao(tTucao);
	}

	/**
	 * 删除吐槽对象
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteTTucaoByIds(String ids) {
		return tTucaoMapper.deleteTTucaoByIds(Convert.toStrArray(ids));
	}

	/**
	 * 删除吐槽信息
	 *
	 * @param id 吐槽ID
	 * @return 结果
	 */
	@Override
	public int deleteTTucaoById(Long id) {
		return tTucaoMapper.deleteTTucaoById(id);
	}
}
