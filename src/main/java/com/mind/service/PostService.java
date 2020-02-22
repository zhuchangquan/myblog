package com.mind.service;

import com.mind.entity.Post;

import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lv-success
 * @since 2018-12-23
 */
public interface PostService extends BaseService<Post> {
	public void incrZsetValueAndUnionForLastWeekRank(Long postId);

    public void zUnionAndStoreLast7DaysForLastWeekRank();

    public void initIndexWeekRank();

    void setViewCount(Map<String, Object> post);
}
