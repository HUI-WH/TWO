package cn.edu.tsu.blog.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CommentDetailDto implements Serializable {

    private Integer id;
    /**
     * 评论人ID
     */
    private Integer uid;
    private String uName;
    /**
     * 文章id
     */
    private Integer aid;
    /**
     * 父评论id
     */
    private Integer pid;
    /**
     * 父评论人的ID
     */
    private Integer pUid;
    /**
     * 父评论人的名称
     */
    private String pUName;
    /**
     * 评论人头像
     */
    private String uIcon;
    /**
     * 评论时间
     */
    private Date commentTime;

    /**
     * 评论点赞数
     */
    private int likes;
    /**
     * 评分
     */
    private Long score;

    /**
     * 评论在多长时间之前
     */
    private String afterTime;

    private String content;

}
