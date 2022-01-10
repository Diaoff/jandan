package com.ruoyi.project.jandan.tucao.service;

import com.ruoyi.project.jandan.tucao.domain.TTucao;

import java.util.List;

/**
 * 吐槽Service接口
 *
 * @author ruoyi
 * @date 2021-07-27
 */
public interface ITTucaoService {
	/**
	 * 查询吐槽
	 *
	 * @param id 吐槽ID
	 * @return 吐槽
	 */
	TTucao selectTTucaoById(Long id);

	/**
	 * 查询吐槽列表
	 *
	 * @param tTucao 吐槽
	 * @return 吐槽集合
	 */
	List<TTucao> selectTTucaoList(TTucao tTucao);

	/**
	 * 新增吐槽
	 *
	 * @param tTucao 吐槽
	 * @return 结果
	 */
	int insertTTucao(TTucao tTucao);

	/**
	 * 修改吐槽
	 *
	 * @param tTucao 吐槽
	 * @return 结果
	 */
	int updateTTucao(TTucao tTucao);

	/**
	 * 批量删除吐槽
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	int deleteTTucaoByIds(String ids);

	/**
	 * 删除吐槽信息
	 *
	 * @param id 吐槽ID
	 * @return 结果
	 */
	int deleteTTucaoById(Long id);
}
