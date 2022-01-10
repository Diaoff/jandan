package com.ruoyi.project.jandan.tucao.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.jandan.tucao.domain.TTucao;
import com.ruoyi.project.jandan.tucao.service.ITTucaoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 吐槽Controller
 *
 * @author ruoyi
 * @date 2021-07-27
 */
@Controller
@RequestMapping("/jandan/tucao")
public class TTucaoController extends BaseController {
	private String prefix = "jandan/tucao";

	@Autowired
	private ITTucaoService tTucaoService;

	@RequiresPermissions("jandan:tucao:view")
	@GetMapping()
	public String tucao() {
		return prefix + "/tucao";
	}

	/**
	 * 查询吐槽列表
	 */
	@RequiresPermissions("jandan:tucao:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TTucao tTucao) {
		startPage();
		List<TTucao> list = tTucaoService.selectTTucaoList(tTucao);
		return getDataTable(list);
	}

	/**
	 * 导出吐槽列表
	 */
	@RequiresPermissions("jandan:tucao:export")
	@Log(title = "吐槽", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(TTucao tTucao) {
		List<TTucao> list = tTucaoService.selectTTucaoList(tTucao);
		ExcelUtil<TTucao> util = new ExcelUtil<TTucao>(TTucao.class);
		return util.exportExcel(list, "吐槽数据");
	}

	/**
	 * 新增吐槽
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存吐槽
	 */
	@RequiresPermissions("jandan:tucao:add")
	@Log(title = "吐槽", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TTucao tTucao) {
		return toAjax(tTucaoService.insertTTucao(tTucao));
	}

	/**
	 * 修改吐槽
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap) {
		TTucao tTucao = tTucaoService.selectTTucaoById(id);
		mmap.put("tTucao", tTucao);
		return prefix + "/edit";
	}

	/**
	 * 修改保存吐槽
	 */
	@RequiresPermissions("jandan:tucao:edit")
	@Log(title = "吐槽", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TTucao tTucao) {
		return toAjax(tTucaoService.updateTTucao(tTucao));
	}

	/**
	 * 删除吐槽
	 */
	@RequiresPermissions("jandan:tucao:remove")
	@Log(title = "吐槽", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(tTucaoService.deleteTTucaoByIds(ids));
	}
}
