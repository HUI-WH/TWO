package cn.edu.tsu.blog.model;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "article")
public class Article {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "author_id")
    private Integer authorId;

    /**
     * 摘要
     */
    @Column(name = "`describe`")
    private String describe;

    @Column(name = "title_img")
    private String titleImg;

    @Column(name = "content")
    private String content;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 文章内容类型 0：md, 1:html
     */
    @Column(name = "content_type")
    private Integer contentType;
    /**
     * 文章类别id
     */
    @Column(name="cid")
    private Integer cid;
}