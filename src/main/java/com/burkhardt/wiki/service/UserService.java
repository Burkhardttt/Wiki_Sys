package com.burkhardt.wiki.service;

import com.burkhardt.wiki.domain.User;
import com.burkhardt.wiki.domain.UserExample;
import com.burkhardt.wiki.exception.BusinessException;
import com.burkhardt.wiki.exception.BusinessExceptionCode;
import com.burkhardt.wiki.mapper.UserMapper;
import com.burkhardt.wiki.req.UserQueryReq;
import com.burkhardt.wiki.req.UserSaveReq;
import com.burkhardt.wiki.resp.PageResp;
import com.burkhardt.wiki.resp.UserQueryResp;
import com.burkhardt.wiki.util.CopyUtil;
import com.burkhardt.wiki.util.SnowFlake;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Resource
	private UserMapper userMapper;

	@Resource
	private SnowFlake snowFlake;

	public PageResp<UserQueryResp> list(UserQueryReq req) {
		UserExample userExample = new UserExample();
		UserExample.Criteria criteria = userExample.createCriteria();
		if (!ObjectUtils.isEmpty(req.getLoginName())) {
			criteria.andLoginNameEqualTo(req.getLoginName());
		}
		PageHelper.startPage(req.getPage(), req.getSize());
		List<User> userList = userMapper.selectByExample(userExample);

		PageInfo<User> pageInfo = new PageInfo<>(userList);
		LOG.info("总行数：{}", pageInfo.getTotal());
		LOG.info("总页数：{}", pageInfo.getPages());

		// List<UserResp> respList = new ArrayList<>();
		// for (User user : userList) {
		//     // UserResp userResp = new UserResp();
		//     // BeanUtils.copyProperties(user, userResp);
		//     // 对象复制
		//     UserResp userResp = CopyUtil.copy(user, UserResp.class);
		//
		//     respList.add(userResp);
		// }

		// 列表复制
		List<UserQueryResp> list = CopyUtil.copyList(userList, UserQueryResp.class);

		PageResp<UserQueryResp> pageResp = new PageResp();
		pageResp.setTotal(pageInfo.getTotal());
		pageResp.setList(list);

		return pageResp;
	}

	/**
	 * 保存
	 */
	public void save(UserSaveReq req) {
		User user = CopyUtil.copy(req, User.class);
		if (ObjectUtils.isEmpty(req.getId())) {
			User userDB = selectByLoginName(req.getLoginName());
			if (ObjectUtils.isEmpty(userDB)) {
				// 新增
				user.setId(snowFlake.nextId());
				userMapper.insert(user);
			} else {
				// 用户名已存在
				throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
			}
		} else {
			// 更新
			user.setLoginName(null);
			user.setPassword(null);
			userMapper.updateByPrimaryKeySelective(user);
		}
	}

	public void delete(Long id) {
		userMapper.deleteByPrimaryKey(id);
	}

	public User selectByLoginName(String LoginName) {
		UserExample userExample = new UserExample();
		UserExample.Criteria criteria = userExample.createCriteria();
		criteria.andLoginNameEqualTo(LoginName);
		List<User> userList = userMapper.selectByExample(userExample);
		if (CollectionUtils.isEmpty(userList)) {
			return null;
		} else {
			return userList.get(0);
		}
	}
}
