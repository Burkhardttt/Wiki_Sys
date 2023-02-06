package com.burkhardt.wiki.controller;

import com.burkhardt.wiki.domain.Ebook;
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
	public List<Ebook> list(){
		return ebookService.list();
	}

}
