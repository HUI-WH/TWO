package cn.edu.tsu.blog.service.impl;

import cn.edu.tsu.blog.model.BlogClass;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.edu.tsu.blog.mapper.BlogClassMapper;
import cn.edu.tsu.blog.service.BlogClassService;

import java.util.List;

@Service
public class BlogClassServiceImpl implements BlogClassService{

    @Resource
    private BlogClassMapper blogClassMapper;

    @Override
    public List<BlogClass> selectAll() {
        return blogClassMapper.selectAll();
    }
}
