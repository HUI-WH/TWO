package cn.edu.tsu.blog.service;

import cn.edu.tsu.blog.model.Label;

import java.util.List;

public interface LabelService{

    List<Label> selectAll();

    int insert(Label label);

    int delete(int id);

    int update(Label label);


}
