package cn.edu.tsu.blog.commons.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserDetailInfoDto implements Serializable {

    private Integer id;
    private String code;
    private String email;
    private String name;
    private String icon;
    private Date createTime;
    private Integer blogAge;
    private Integer readingNum;
    /**
     * 粉丝数量
     */
    private Integer fansNum;
    /**
     * 收到评论数量
     */
    private Integer commentNum;

    /**
     * 收藏数量
     */
    private Integer collectionNum;
    /**
     * 关注数量
     */
    private Integer followNum;

    /**
     * 博客文章数量
     */
    private Integer articleNum;
    /**
     * 获得赞数
     */
    private Integer likesNum;
}
