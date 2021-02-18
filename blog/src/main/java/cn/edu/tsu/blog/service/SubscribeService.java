package cn.edu.tsu.blog.service;

import cn.edu.tsu.blog.model.Subscribe;
import com.github.pagehelper.PageInfo;

public interface SubscribeService{

    int insert(Subscribe subscribe);

    int delete(Integer id);

    int update(Subscribe subscribe);

    PageInfo<Subscribe> selectByPage(int pageNum, int pageSize, String keyword);

}
