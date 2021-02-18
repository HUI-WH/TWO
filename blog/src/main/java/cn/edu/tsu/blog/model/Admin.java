package cn.edu.tsu.blog.model;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "`admin`")
public class Admin {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "`name`")
    private String name;

    @Column(name = "`password`")
    private String password;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "login_time")
    private Date loginTime;

    @Column(name = "icon")
    private String icon;

    @Column(name = "email")
    private String email;
}