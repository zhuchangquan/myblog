package com.mind.service.impl;

import com.mind.entity.Comment;
import com.mind.mapper.CommentMapper;
import com.mind.service.CommentService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lv-success
 * @since 2018-12-23
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

	@Override
	public void join(Map<String, Object> stringObjectMap, String field) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void join(List<Map<String, Object>> datas, String field) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void join(IPage<Map<String, Object>> pageData, String field) {
		// TODO Auto-generated method stub
		
	}

}
