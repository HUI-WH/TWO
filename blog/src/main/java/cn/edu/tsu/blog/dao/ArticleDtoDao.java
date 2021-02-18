package cn.edu.tsu.blog.dao;

import cn.edu.tsu.blog.commons.dto.ArticleDto;
import tk.mybatis.mapper.MyMapper;

public interface ArticleDtoDao extends MyMapper<ArticleDto> {
    ArticleDto get(Integer id);
}
