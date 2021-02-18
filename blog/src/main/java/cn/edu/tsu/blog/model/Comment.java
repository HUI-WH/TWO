package cn.edu.tsu.blog.model;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "`comment`")
public class Comment {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 评论人ID
     */
    @Column(name = "`uid`")
    private Integer uid;

    @Column(name = "content")
    private String content;

    /**
     * 文章评分
     */
    @Column(name = "article_score")
    private Long articleScore;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 点赞数
     */
    @Column(name = "likes")
    private Integer likes;

    /**
     * 文章id
     */
    @Column(name = "aid")
    private Integer aid;

    /**
     * 父评论
     */
    @Column(name = "pid")
    private Integer pid;
}