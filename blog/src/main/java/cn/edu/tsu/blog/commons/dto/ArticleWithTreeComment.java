package cn.edu.tsu.blog.commons.dto;

import cn.edu.tsu.blog.dto.CommentDetailTreeDto;
import cn.edu.tsu.blog.model.Articleinfo;
import cn.edu.tsu.blog.model.Label;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ArticleWithTreeComment implements Serializable {

    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 作者id
     */
    private Integer authorId;
    /**
     * 作者名称
     */
    private String authorName;
    /**
     * 简介
     */
    private String describe;
    /**
     * 标题图片
     */
    private String titleImg;
    /**
     * 内容
     */
    private String content;
    /**
     * 信息
     */
    private Articleinfo articleinfo;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 树形评论列表
     */
    private TreeCommentDto treeCommentDto;
    private List<Label> labels;
    private List<CommentDetailTreeDto> detailTreeDto;

}
