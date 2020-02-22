package com.mind.service;

import com.mind.entity.User;
import com.mind.shiro.AccountProfile;

import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lv-success
 * @since 2018-12-23
 */
public interface UserService extends BaseService<User> {
	
	/**
     * 用于用户登录
     * AccountProfile是有用户基本信息的类，包括私信、通知数量，头像等
     * @param email
     * @param password
     * @return
     */
	AccountProfile login(String email,String password);
	
	/**
	 *      注册
	 * @param user
	 * @return
	 */
	R register(User user);
	
	/*
	 * 然后因为文章信息关联了用户信息，在首页内容显示需要显示作者的头像、用户名等信息，所以，
	 * 这里加了一个userService.join，定义两个参数，一个分页结果，一个是关联字段，这样
	 * 以后每个有关联用户信息的分页分页结果都可以使用这个方法
	 */
//	void join(IPage<Map<String, Object>> pageData,String linkfield);
	
}
