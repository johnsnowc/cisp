package com.snowc.cisp.service;

import com.snowc.cisp.domain.Category;

import java.util.List;


public interface CategoryService {

    List<Category> getAllCategory();

    void create(Category category);

    void deleteById(Integer id);

    void updateById(Category category);
}
