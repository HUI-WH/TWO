package cn.edu.tsu.blog.service.impl;

import cn.edu.tsu.blog.model.Subscribe;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.edu.tsu.blog.mapper.SubscribeMapper;
import cn.edu.tsu.blog.service.SubscribeService;

import java.util.List;

@Service
public class SubscribeServiceImpl implements SubscribeService{

    @Resource
    private SubscribeMapper subscribeMapper;

    @Override
    public int insert(Subscribe subscribe) {
        return subscribeMapper.insertSelective(subscribe);
    }

    @Override
    public int delete(Integer id) {
        return subscribeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Subscribe subscribe) {
        return subscribeMapper.updateByPrimaryKeySelective(subscribe);
    }

    @Override
    public PageInfo<Subscribe> selectByPage(int pageNum, int pageSize, String keyword) {
        PageHelper.startPage(pageNum, pageSize);
        List<Subscribe> subscribes = subscribeMapper.selectAll();

        return new PageInfo<>(subscribes);
    }
}
