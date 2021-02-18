package cn.edu.tsu.blog.service;

import cn.edu.tsu.blog.model.Labelclass;

import java.util.List;

public interface LabelclassService{

    /**
     * 查询所有
     * @return
     */
    List<Labelclass> selectAll();

    /**
     * 添加
     * @param labelclass
     * @return
     */
    int insertClass(Labelclass labelclass);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteClass(Integer id);

    int updateClass(Labelclass labelclass);

}
