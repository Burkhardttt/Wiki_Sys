package com.burkhardt.wiki.service;

import com.burkhardt.wiki.domain.Category;
import com.burkhardt.wiki.domain.CategoryExample;
import com.burkhardt.wiki.mapper.CategoryMapper;
import com.burkhardt.wiki.req.CategoryQueryReq;
import com.burkhardt.wiki.req.CategorySaveReq;
import com.burkhardt.wiki.resp.CategoryQueryResp;
import com.burkhardt.wiki.resp.PageResp;
import com.burkhardt.wiki.util.CopyUtil;
import com.burkhardt.wiki.util.SnowFlake;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryService {

	private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);

	@Resource
	private CategoryMapper categoryMapper;

	@Resource
	private SnowFlake snowFlake;

	public PageResp<CategoryQueryResp> list(CategoryQueryReq req) {

		CategoryExample categoryExample = new CategoryExample();
		CategoryExample.Criteria criteria = categoryExample.createCriteria();

		PageHelper.startPage(req.getPage(), req.getSize());
		List<Category> categoryList = categoryMapper.selectByExample(categoryExample);

		PageInfo<Category> pageInfo = new PageInfo<>(categoryList);
		LOG.info("Total number of rows: " + pageInfo.getTotal());
		LOG.info("Total number of pages: " + pageInfo.getPages());

//		List<CategoryQueryResp> respList = new ArrayList<>();
//		for (Category category : categoryList) {
////			CategoryQueryResp categoryResp = new CategoryQueryResp();
////			BeanUtils.copyProperties(category, categoryResp);
//			CategoryQueryResp categoryResp = CopyUtil.copy(category, CategoryQueryResp.class);
//			respList.add(categoryResp); // single object copy
//		}

		// list copy
		List<CategoryQueryResp> list = CopyUtil.copyList(categoryList, CategoryQueryResp.class);

		PageResp<CategoryQueryResp> pageResp = new PageResp<>();
		pageResp.setTotal(pageInfo.getTotal());
		pageResp.setList(list);

		return pageResp;
	}

	/**
	 * save
	 */
	public void save(CategorySaveReq req){
		Category category = CopyUtil.copy(req, Category.class);
		if (ObjectUtils.isEmpty(req.getId())) {
			// insertion
			category.setId(snowFlake.nextId());
			categoryMapper.insert(category);
		}
		else {
			// update
			categoryMapper.updateByPrimaryKey(category);
		}
	}

	public void delete(Long id){
		categoryMapper.deleteByPrimaryKey(id);
	}
}
