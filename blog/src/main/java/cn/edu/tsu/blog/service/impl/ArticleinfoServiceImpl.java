package cn.edu.tsu.blog.service.impl;

import cn.edu.tsu.blog.model.Articleinfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.edu.tsu.blog.mapper.ArticleinfoMapper;
import cn.edu.tsu.blog.service.ArticleinfoService;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ArticleinfoServiceImpl implements ArticleinfoService{

    @Resource
    private ArticleinfoMapper articleinfoMapper;

    @Override
    public int insert(Articleinfo articleinfo) {
        return articleinfoMapper.insert(articleinfo);
    }

    @Override
    public int delete(Integer id) {
        return articleinfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Articleinfo articleinfo) {
        return articleinfoMapper.updateByPrimaryKeySelective(articleinfo);
    }

    @Override
    public PageInfo<Articleinfo> selectByPage(int pageNum, int pageSize, String keyword) {
        PageHelper.startPage(pageNum,pageSize);

        List<Articleinfo> articleinfos = articleinfoMapper.selectAll();

        return new PageInfo<>(articleinfos);
    }

    @Override
    public Articleinfo getInfoByAid(Integer id) {

        Example e = new Example(Articleinfo.class);

        e.createCriteria().andEqualTo("article_id",id);

        return articleinfoMapper.selectOneByExample(e);
    }
}
