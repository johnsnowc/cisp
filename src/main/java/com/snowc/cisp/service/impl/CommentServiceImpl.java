package com.snowc.cisp.service.impl;

import com.snowc.cisp.domain.Comment;
import com.snowc.cisp.domain.User;
import com.snowc.cisp.service.CommentService;
import com.snowc.cisp.dao.ArticleMapper;
import com.snowc.cisp.dao.CommentMapper;
import com.snowc.cisp.dao.UserMapper;
import com.snowc.cisp.vo.ArticleVO;
import com.snowc.cisp.vo.CommentVO;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;


@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    CommentMapper commentMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    ArticleMapper articleMapper;

    @Override
    public void create(Comment comment) {
        commentMapper.create(comment);
        articleMapper.addComment(comment.getArticleId());
    }

    @Override
    public List<CommentVO> getCommentByArticleId(Integer articleId) {
        List<CommentVO> commentVOList = commentMapper.getCommentByArticleId(articleId);
        for (CommentVO commentVO : commentVOList) {
            User user = userMapper.getUserById(commentVO.getUserId());
            commentVO.setAvatar(user.getAvatar());
            commentVO.setNickname(user.getNickname());
        }
        return commentVOList;
    }

    @Override
    public int countAllComment() {
        List<ArticleVO> articleVOList = articleMapper.getIndexArticles(null);
        int count = 0;
        for (ArticleVO articleVO : articleVOList) {
            count += this.getCommentByArticleId(articleVO.getId()).size();
        }
        return count;
    }

    @Override
    public void delete(Comment comment) {
        Comment tmpComment = commentMapper.getById(comment.getId());
        commentMapper.delete(comment);
        articleMapper.deleteComment(tmpComment.getArticleId());
    }
}
