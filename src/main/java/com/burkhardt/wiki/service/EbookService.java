package com.burkhardt.wiki.service;

import com.burkhardt.wiki.domain.Ebook;
import com.burkhardt.wiki.domain.EbookExample;
import com.burkhardt.wiki.mapper.EbookMapper;
import com.burkhardt.wiki.req.EbookReq;
import com.burkhardt.wiki.resp.EbookResp;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {

	@Resource
	private EbookMapper ebookMapper;

	public List<EbookResp> list(EbookReq req) {
		EbookExample ebookExample = new EbookExample();
		EbookExample.Criteria criteria = ebookExample.createCriteria();
		criteria.andNameLike("%" + req.getName() + "%");
		List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

		List<EbookResp> respList = new ArrayList<>();
		for (Ebook ebook : ebookList) {
			EbookResp ebookResp = new EbookResp();
			BeanUtils.copyProperties(ebook, ebookResp);
			respList.add(ebookResp);
		}
		return respList;
	}
}
