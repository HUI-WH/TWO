package cn.edu.tsu.blog.service;

import cn.edu.tsu.blog.model.Comment;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CommentService {

    Integer insert(Comment comment);

    Integer delete(Integer id);

    Integer update(Comment comment);

    Integer delete(Integer cid,Integer uid,Integer aid);
    Comment getById(Integer id);

    PageInfo<Comment> selectByPage(int pageNum, int pageSize, String keyword);

    List<Comment> selectByAid(Integer aid);

    /**
     * 通过评论的主键批量删除
     * @return
     */
    Integer deleteByList(List<Integer> idList);

}

