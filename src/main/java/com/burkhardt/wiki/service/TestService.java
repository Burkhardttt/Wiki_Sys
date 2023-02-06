package com.burkhardt.wiki.service;

import com.burkhardt.wiki.domain.Test;
import com.burkhardt.wiki.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {

	@Resource
	private TestMapper testMapper;

	public List<Test> list() {
		return testMapper.list();
	}
}
