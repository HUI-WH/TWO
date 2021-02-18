package cn.edu.tsu.blog.service.impl;

import cn.edu.tsu.blog.model.Label;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.edu.tsu.blog.mapper.LabelMapper;
import cn.edu.tsu.blog.service.LabelService;

import java.util.List;

@Service
public class LabelServiceImpl implements LabelService{

    @Resource
    private LabelMapper labelMapper;

    @Override
    public List<Label> selectAll() {
        return labelMapper.selectAll();
    }

    @Override
    public int insert(Label label) {
        if(valid(label)){
            return labelMapper.insertSelective(label);
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        return labelMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Label label) {
        if(valid(label)){
            return labelMapper.updateByPrimaryKeySelective(label);
        }
        return 0;
    }

    private boolean valid(Label label){

        if(label!=null&&label.getPid()!=null){
            if(label.getPid()==null){
                label.setPid(0);
            }
            return true;
        }

        return false;
    }

}
