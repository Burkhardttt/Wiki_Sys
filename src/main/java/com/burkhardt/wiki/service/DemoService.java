package com.burkhardt.wiki.service;

import com.burkhardt.wiki.domain.Demo;
import com.burkhardt.wiki.mapper.DemoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DemoService {

	@Resource
	private DemoMapper demoMapper;

	public List<Demo> list() {
		return demoMapper.selectByExample(null);
	}
}
