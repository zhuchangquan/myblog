package com.mind.service.impl;

import com.mind.entity.Category;
import com.mind.mapper.CategoryMapper;
import com.mind.service.CategoryService;
import java.util.HashMap;
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
public class CategoryServiceImpl extends BaseServiceImpl<CategoryMapper, Category> implements CategoryService {
	@Override
    public void join(Map<String, Object> map, String field) {

		if(map == null || map.get(field) == null) return;

        Map<String, Object> joinColumns = new HashMap<>();

        //字段的值
        String linkfieldValue = map.get(field).toString();
        Category category = this.getById(linkfieldValue);

        joinColumns.put("id", category.getId());
        joinColumns.put("name", category.getName());
        joinColumns.put("icon", category.getIcon());

        map.put("category", joinColumns);
    }
}
