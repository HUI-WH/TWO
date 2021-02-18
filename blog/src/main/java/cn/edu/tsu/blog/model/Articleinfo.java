package cn.edu.tsu.blog.model;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "articleinfo")
public class Articleinfo {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 文章id
     */
    @Column(name = "article_id")
    private Integer articleId;

    /**
     * 点赞数
     */
    @Column(name = "likes")
    private Integer likes;

    /**
     * 浏览数
     */
    @Column(name = "readings")
    private Integer readings;

    /**
     * 收藏数
     */
    @Column(name = "collections")
    private Integer collections;

    /**
     * 评论数
     */
    @Column(name = "comments")
    private Integer comments;
}