package cn.edu.tsu.blog.model;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "`user`")
public class User {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 账号
     */
    @Column(name = "code")
    private String code;

    /**
     * 密码
     */
    @Column(name = "`password`")
    private String password;

    /**
     * 名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 注册时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后登录时间
     */
    @Column(name = "login_time")
    private Date loginTime;

    /**
     * 头像地址
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;
}