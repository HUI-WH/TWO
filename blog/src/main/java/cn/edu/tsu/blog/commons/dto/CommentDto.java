package cn.edu.tsu.blog.commons.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
public class CommentDto implements Serializable {
    private Integer id;

    /**
     * 评论人ID
     */
    private Integer uid;
    /**
     * 评论人名称
     */
    private String uName;
    /**
     * 评论内容
     */
    private String content;

    /**
     * 文章评分
     */
    private Long articleScore;

    private Date createTime;

    /**
     * 点赞数
     */
    private Integer likes;

    /**
     * 文章id
     */
    private Integer aid;

    /**
     * 父评论
     */
    private Integer pid;

    /**
     * 评论人头像
     */
    private String uIcon;

    /**
     * 评论在多长时间之前
     */
    private String AfterTime;

}
