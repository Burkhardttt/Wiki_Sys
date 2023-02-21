package com.burkhardt.wiki.controller;

import com.burkhardt.wiki.req.DocQueryReq;
import com.burkhardt.wiki.req.DocSaveReq;
import com.burkhardt.wiki.resp.CommonResp;
import com.burkhardt.wiki.resp.DocQueryResp;
import com.burkhardt.wiki.resp.PageResp;
import com.burkhardt.wiki.service.DocService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/doc")
public class DocController {

	@Resource
	private DocService docService;

	@GetMapping("/all")
	public CommonResp all() {
		CommonResp<List<DocQueryResp>> resp = new CommonResp<>();
		List<DocQueryResp> list = docService.all();
		resp.setContent(list);
		return resp;
	}

	@GetMapping("/list")
	public CommonResp list(@Valid DocQueryReq req) {
		CommonResp<PageResp<DocQueryResp>> resp = new CommonResp<>();
		PageResp<DocQueryResp> list = docService.list(req);
		resp.setContent(list);
		return resp;
	}

	@PostMapping("/save")
	public CommonResp save(@Valid @RequestBody DocSaveReq req) {
		CommonResp resp = new CommonResp<>();
		docService.save(req);
		return resp;
	}

	@DeleteMapping("/delete/{id}")
	public CommonResp delete(@PathVariable Long id) {
		CommonResp resp = new CommonResp<>();
		docService.delete(id);
		return resp;
	}
}
