package com.burkhardt.wiki.service;

import com.burkhardt.wiki.domain.Ebook;
import com.burkhardt.wiki.domain.EbookExample;
import com.burkhardt.wiki.mapper.EbookMapper;
import com.burkhardt.wiki.req.EbookReq;
import com.burkhardt.wiki.resp.EbookResp;
import com.burkhardt.wiki.resp.PageResp;
import com.burkhardt.wiki.util.CopyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

	private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);

	@Resource
	private EbookMapper ebookMapper;

	public PageResp<EbookResp> list(EbookReq req) {

		EbookExample ebookExample = new EbookExample();
		EbookExample.Criteria criteria = ebookExample.createCriteria();
		if (!ObjectUtils.isEmpty(req.getName())) {
			criteria.andNameLike("%" + req.getName() + "%");
		}

		PageHelper.startPage(req.getPage(), req.getSize());
		List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

		PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList);
		LOG.info("Total number of rows: " + pageInfo.getTotal());
		LOG.info("Total number of pages: " + pageInfo.getPages());

//		List<EbookResp> respList = new ArrayList<>();
//		for (Ebook ebook : ebookList) {
////			EbookResp ebookResp = new EbookResp();
////			BeanUtils.copyProperties(ebook, ebookResp);
//			EbookResp ebookResp = CopyUtil.copy(ebook, EbookResp.class);
//			respList.add(ebookResp); // single object copy
//		}

		// list copy
		List<EbookResp> list = CopyUtil.copyList(ebookList, EbookResp.class);

		PageResp<EbookResp> pageResp = new PageResp<>();
		pageResp.setTotal(pageInfo.getTotal());
		pageResp.setList(list);

		return pageResp;
	}
}
