package cn.edu.tsu.blog.service.impl;

import cn.edu.tsu.blog.model.Labelclass;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.edu.tsu.blog.mapper.LabelclassMapper;
import cn.edu.tsu.blog.service.LabelclassService;

import java.util.List;

@Service
public class LabelclassServiceImpl implements LabelclassService{

    @Resource
    private LabelclassMapper labelclassMapper;

    @Override
    public List<Labelclass> selectAll() {
        return labelclassMapper.selectAll();
    }

    @Override
    public int insertClass(Labelclass labelclass) {
        return labelclassMapper.insertSelective(labelclass);
    }

    @Override
    public int deleteClass(Integer id) {
        return labelclassMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateClass(Labelclass labelclass) {
        return labelclassMapper.updateByPrimaryKey(labelclass);
    }
}
