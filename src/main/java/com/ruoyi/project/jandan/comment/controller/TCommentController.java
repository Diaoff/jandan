package com.ruoyi.project.jandan.comment.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.jandan.comment.domain.TComment;
import com.ruoyi.project.jandan.comment.service.ITCommentService;
import com.ruoyi.project.jandan.images.domain.Images;
import com.ruoyi.project.jandan.images.service.IImagesService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 帖子Controller
 *
 * @author diaoff
 * @date 2021-07-27
 */
@Controller
@RequestMapping("/jandan/comment")
public class TCommentController extends BaseController {
	private String prefix = "jandan/comment";

	@Autowired
	private ITCommentService tCommentService;
	@Autowired
	private IImagesService imagesService;

	@RequiresPermissions("jandan:comment:view")
	@GetMapping()
	public String comment() {
		return prefix + "/comment";
	}

	/**
	 * 查询帖子列表
	 */
	@RequiresPermissions("jandan:comment:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TComment tComment) {
		startPage();
		List<TComment> list = tCommentService.selectTCommentList(tComment);
		return getDataTable(list);
	}

	/**
	 * 导出帖子列表
	 */
	@RequiresPermissions("jandan:comment:export")
	@Log(title = "帖子", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(TComment tComment) {
		List<TComment> list = tCommentService.selectTCommentList(tComment);
		ExcelUtil<TComment> util = new ExcelUtil<TComment>(TComment.class);
		return util.exportExcel(list, "帖子数据");
	}

	/**
	 * 新增帖子
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存帖子
	 */
	@RequiresPermissions("jandan:comment:add")
	@Log(title = "帖子", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TComment tComment) {
		return toAjax(tCommentService.insertTComment(tComment));
	}

	/**
	 * 修改帖子
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap) {
		TComment tComment = tCommentService.selectTCommentById(id);
		mmap.put("tComment", tComment);
		return prefix + "/edit";
	}

	/**
	 * 修改保存帖子
	 */
	@RequiresPermissions("jandan:comment:edit")
	@Log(title = "帖子", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TComment tComment) {
		return toAjax(tCommentService.updateTComment(tComment));
	}

	/**
	 * 删除帖子
	 */
	@RequiresPermissions("jandan:comment:remove")
	@Log(title = "帖子", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(tCommentService.deleteTCommentByIds(ids));
	}

	/**
	 * 查看帖子
	 */
	@GetMapping("/view/{id}")
	public String view(@PathVariable("id") Long id, ModelMap mmap) {
		TComment tComment = tCommentService.selectTCommentById(id);
		mmap.put("tComment", tComment);
		return prefix + "/view";
	}

	@GetMapping(value = "/index")
	public String index(TComment tComment) {
		return prefix + "/index";
	}

	/**
	 * 查询帖子列表
	 */
	@RequiresPermissions("jandan:comment:list")
	@PostMapping("/home")
	@ResponseBody
	public TableDataInfo home(TComment tComment) {
		startPage();
		List<TComment> list = tCommentService.selectTCommentImagesList(tComment);
		return getDataTable(list);
	}
}
