package com.ruoyi.project.jandan.images.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.jandan.images.domain.Images;
import com.ruoyi.project.jandan.images.service.IImagesService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 图片Controller
 *
 * @author diaoff
 * @date 2021-07-27
 */
@Controller
@RequestMapping("/jandan/images")
public class ImagesController extends BaseController {
	private String prefix = "jandan/images";

	@Autowired
	private IImagesService imagesService;

	@RequiresPermissions("jandan:images:view")
	@GetMapping()
	public String images() {
		return prefix + "/images";
	}

	/**
	 * 查询图片列表
	 */
	@RequiresPermissions("jandan:images:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Images images) {
		startPage();
		List<Images> list = imagesService.selectImagesList(images);
		return getDataTable(list);
	}

	/**
	 * 导出图片列表
	 */
	@RequiresPermissions("jandan:images:export")
	@Log(title = "图片", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(Images images) {
		List<Images> list = imagesService.selectImagesList(images);
		ExcelUtil<Images> util = new ExcelUtil<Images>(Images.class);
		return util.exportExcel(list, "图片数据");
	}

	/**
	 * 新增图片
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存图片
	 */
	@RequiresPermissions("jandan:images:add")
	@Log(title = "图片", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Images images) {
		return toAjax(imagesService.insertImages(images));
	}

	/**
	 * 修改图片
	 */
	@GetMapping("/edit/{url}")
	public String edit(@PathVariable("url") String url, ModelMap mmap) {
		Images images = imagesService.selectImagesById(url);
		mmap.put("images", images);
		return prefix + "/edit";
	}

	/**
	 * 修改保存图片
	 */
	@RequiresPermissions("jandan:images:edit")
	@Log(title = "图片", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Images images) {
		return toAjax(imagesService.updateImages(images));
	}

	/**
	 * 删除图片
	 */
	@RequiresPermissions("jandan:images:remove")
	@Log(title = "图片", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(imagesService.deleteImagesByIds(ids));
	}
}
