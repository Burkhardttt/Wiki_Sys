package com.burkhardt.wiki.service;

import com.burkhardt.wiki.domain.Ebook;
import com.burkhardt.wiki.domain.EbookExample;
import com.burkhardt.wiki.mapper.EbookMapper;
import com.burkhardt.wiki.req.EbookQueryReq;
import com.burkhardt.wiki.req.EbookSaveReq;
import com.burkhardt.wiki.resp.EbookQueryResp;
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

	public PageResp<EbookQueryResp> list(EbookQueryReq req) {

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

//		List<EbookQueryResp> respList = new ArrayList<>();
//		for (Ebook ebook : ebookList) {
////			EbookQueryResp ebookResp = new EbookQueryResp();
////			BeanUtils.copyProperties(ebook, ebookResp);
//			EbookQueryResp ebookResp = CopyUtil.copy(ebook, EbookQueryResp.class);
//			respList.add(ebookResp); // single object copy
//		}

		// list copy
		List<EbookQueryResp> list = CopyUtil.copyList(ebookList, EbookQueryResp.class);

		PageResp<EbookQueryResp> pageResp = new PageResp<>();
		pageResp.setTotal(pageInfo.getTotal());
		pageResp.setList(list);

		return pageResp;
	}

	/**
	 * save
	 */
	public void save(EbookSaveReq req){
		Ebook ebook = CopyUtil.copy(req, Ebook.class);
		if (ObjectUtils.isEmpty(req.getId())) { // insertion
			ebookMapper.insert(ebook);
		}
		else {// update
			ebookMapper.updateByPrimaryKey(ebook);
		}
	}

}
