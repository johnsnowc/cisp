package com.snowc.cisp.dao;

import com.snowc.cisp.domain.Category;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface CategoryMapper {

  List<Category> getAllCategory();

  void create(Category category);

  void deleteById(Integer id);

  void updateById(Category category);
}
