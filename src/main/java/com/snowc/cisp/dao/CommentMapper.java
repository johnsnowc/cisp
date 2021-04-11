package com.snowc.cisp.dao;

import com.snowc.cisp.domain.Comment;
import com.snowc.cisp.vo.CommentVO;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface CommentMapper {

  void create(Comment comment);

  List<CommentVO> getCommentByArticleId(Integer articleId);

  void delete(Comment comment);

  Comment getById(Integer id);

  void deleteByArticleId(Integer articleId);
}
