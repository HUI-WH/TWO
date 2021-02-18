package cn.edu.tsu.blog.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CommentDetailTreeDto implements Serializable {
    private int id;
    private CommentDetailDto info;
    private List<CommentDetailDto> children;
}
