package com.burkhardt.wiki.controller;

import com.burkhardt.wiki.req.CategoryQueryReq;
import com.burkhardt.wiki.req.CategorySaveReq;
import com.burkhardt.wiki.resp.CategoryQueryResp;
import com.burkhardt.wiki.resp.CommonResp;
import com.burkhardt.wiki.resp.PageResp;
import com.burkhardt.wiki.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Resource
	private CategoryService categoryService;

	@GetMapping("/all")
	public CommonResp all() {
		CommonResp<List<CategoryQueryResp>> resp = new CommonResp<>();
		List<CategoryQueryResp> list = categoryService.all();
		resp.setContent(list);
		return resp;
	}
	@GetMapping("/list")
	public CommonResp list(@Valid CategoryQueryReq req) {
		CommonResp<PageResp<CategoryQueryResp>> resp = new CommonResp<>();
		PageResp<CategoryQueryResp> list = categoryService.list(req);
		resp.setContent(list);
		return resp;
	}

	@PostMapping("/save")
	public CommonResp save(@Valid @RequestBody CategorySaveReq req) {
		CommonResp resp = new CommonResp<>();
		categoryService.save(req);
		return resp;
	}

	@DeleteMapping("/delete/{id}")
	public CommonResp delete(@PathVariable Long id) {
		CommonResp resp = new CommonResp<>();
		categoryService.delete(id);
		return resp;
	}
}