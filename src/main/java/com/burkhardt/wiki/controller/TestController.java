package com.burkhardt.wiki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	/**
	 * GET, POST, PUT, DELETE
	 * /user?id=1
	 * /user/1
	 * @return
	 */

	// http://127.0.0.1:8880/hello
	// RequestMapping可接受所有类型的请求(get, post, put, delete)
	//	@RequestMapping("/hello")
	@GetMapping("/hello")
	public String hello(){
		return "Hello World";
	}
}
