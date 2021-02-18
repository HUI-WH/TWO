package cn.edu.tsu.blog.commons.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TreeCommentDto implements Serializable {
    private Integer id;
    private CommentDto info;
    private List<TreeCommentDto> children;

}
