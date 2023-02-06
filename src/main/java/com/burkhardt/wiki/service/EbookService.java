package com.burkhardt.wiki.service;

import com.burkhardt.wiki.domain.Ebook;
import com.burkhardt.wiki.mapper.EbookMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

	@Resource
	private EbookMapper ebookMapper;

	public List<Ebook> list() {
		return ebookMapper.selectByExample(null);
	}
}
