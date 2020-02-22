package com.mind.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mind.shiro.AccountProfile;
import com.mind.service.CategoryService;
import com.mind.service.CommentService;
import com.mind.service.PostService;
import com.mind.service.UserCollectionService;
import com.mind.service.UserMessageService;
import com.mind.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BaseController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	PostService postService;
	
	@Autowired
	HttpServletRequest req;
	
	@Autowired
    CategoryService categoryService;
	
	@Autowired
	CommentService commentService; 
	
	@Autowired
	UserCollectionService userCollectionService;
	
	@Autowired
	UserMessageService userMessageService;
	
	protected AccountProfile getProfile() {
	   return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
	}
	
	protected Long getProfileId() {
        return getProfile().getId();
    }
}
