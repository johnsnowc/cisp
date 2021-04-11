package com.snowc.cisp.service;

import com.snowc.cisp.domain.Article;
import com.snowc.cisp.vo.ArticleVO;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public interface ArticleService {

  void create(Article article);

  List<ArticleVO> getIndexArticles(Integer page);

  int countIndexArticles();

  ArticleVO getArticleById(Integer id);

  List<ArticleVO> getArticlesByUserId(Integer userId);

  List<ArticleVO> getArticlesByCategoryId(Integer category, Integer page);

  int countArticleByCateId();

  int countAllArticle();

  void update(Article article);

  void delete(Integer id);

  void setTopStatus(Integer id);

  List<ArticleVO> getTopArticle();

  List<ArticleVO> searchArticle(Integer categoryId, String startTime, String endTime,
    String keyword);

  List<Article> getHotArticles();
}
