package cn.edu.tsu.blog.dto;

import cn.edu.tsu.blog.model.Comment;
import lombok.Data;

import java.util.List;

@Data
public class TreeComment {

    private Integer id;
    private Comment info;
    private List<TreeComment> children;
}
