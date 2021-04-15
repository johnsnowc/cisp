package com.snowc.cisp.service;

import com.snowc.cisp.domain.Comment;
import com.snowc.cisp.vo.CommentVO;

import java.util.List;


public interface CommentService {

    void create(Comment comment);

    List<CommentVO> getCommentByArticleId(Integer articleId);

    int countAllComment();

    void delete(Comment comment);

}
