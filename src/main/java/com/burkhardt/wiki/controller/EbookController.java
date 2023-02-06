package com.burkhardt.wiki.controller;

import com.burkhardt.wiki.req.EbookReq;
import com.burkhardt.wiki.resp.CommonResp;
import com.burkhardt.wiki.resp.EbookResp;
import com.burkhardt.wiki.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {

	@Resource
	private EbookService ebookService;

	@GetMapping("/list")
	public CommonResp list(EbookReq req){
		CommonResp<List<EbookResp> > resp = new CommonResp<>();
		List<EbookResp> list = ebookService.list(req);
		resp.setContent(list);
		return resp;
	}

}
