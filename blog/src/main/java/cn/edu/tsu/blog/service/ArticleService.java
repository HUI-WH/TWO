package cn.edu.tsu.blog.service;

import cn.edu.tsu.blog.commons.dto.ArticleDto;
import cn.edu.tsu.blog.commons.dto.ArticleWithTreeComment;
import cn.edu.tsu.blog.model.Article;
import com.github.pagehelper.PageInfo;

public interface ArticleService {

    Article getByID(Integer id);

    int insert(Article article);

    int delete(Integer id);

    int update(Article article);

    PageInfo<Article> selectByPage(int pageNum, int pageSize, String keyword,Integer classId,Integer authorId);

    ArticleWithTreeComment getArticleWithAllInfo(int id);
    ArticleWithTreeComment getArticleWithAllDetailInfo(int id);



}

