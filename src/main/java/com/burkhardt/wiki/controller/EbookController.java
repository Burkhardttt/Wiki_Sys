package com.burkhardt.wiki.controller;

import com.burkhardt.wiki.req.EbookQueryReq;
import com.burkhardt.wiki.req.EbookSaveReq;
import com.burkhardt.wiki.resp.CommonResp;
import com.burkhardt.wiki.resp.EbookQueryResp;
import com.burkhardt.wiki.resp.PageResp;
import com.burkhardt.wiki.service.EbookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/ebook")
public class EbookController {

	@Resource
	private EbookService ebookService;

	@GetMapping("/list")
	public CommonResp list(@Valid EbookQueryReq req) {
		CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<>();
		PageResp<EbookQueryResp> list = ebookService.list(req);
		resp.setContent(list);
		return resp;
	}

	@PostMapping("/save")
	public CommonResp save(@Valid @RequestBody EbookSaveReq req) {
		CommonResp resp = new CommonResp<>();
		ebookService.save(req);
		return resp;
	}

	@DeleteMapping("/delete/{id}")
	public CommonResp delete(@PathVariable Long id) {
		CommonResp resp = new CommonResp<>();
		ebookService.delete(id);
		return resp;
	}
}