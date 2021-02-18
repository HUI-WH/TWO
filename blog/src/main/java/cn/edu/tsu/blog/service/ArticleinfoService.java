package cn.edu.tsu.blog.service;

import cn.edu.tsu.blog.model.Articleinfo;
import cn.edu.tsu.blog.model.Comment;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ArticleinfoService{

    int insert(Articleinfo articleinfo);

    int delete(Integer id);

    int update(Articleinfo articleinfo);

    // TODO 有问题用不到
    PageInfo<Articleinfo> selectByPage(int pageNum, int pageSize, String keyword);

    Articleinfo getInfoByAid(Integer id);

}
