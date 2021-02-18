package cn.edu.tsu.blog.model;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
    * 后台用户权限表
    */
@Data
@Table(name = "permission")
public class Permission {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 父级权限id
     */
    @Column(name = "pid")
    private Long pid;

    /**
     * 名称
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 权限值
     */
    @Column(name = "`value`")
    private String value;

    /**
     * 图标
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）
     */
    @Column(name = "`type`")
    private Integer type;

    /**
     * 前端资源路径
     */
    @Column(name = "uri")
    private String uri;

    /**
     * 启用状态；0->禁用；1->启用
     */
    @Column(name = "`status`")
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;
}