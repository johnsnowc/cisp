package com.snowc.cisp.service.impl;

import com.snowc.cisp.domain.Category;
import com.snowc.cisp.service.CategoryService;
import com.snowc.cisp.dao.CategoryMapper;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceImpl implements CategoryService {

  @Resource
  CategoryMapper categoryMapper;

  @Override
  public List<Category> getAllCategory() {
    return categoryMapper.getAllCategory();
  }

  @Override
  public void create(Category category) {
    categoryMapper.create(category);
  }

  @Override
  public void deleteById(Integer id) {
    categoryMapper.deleteById(id);
  }

  @Override
  public void updateById(Category category) {
    categoryMapper.updateById(category);
  }
}
