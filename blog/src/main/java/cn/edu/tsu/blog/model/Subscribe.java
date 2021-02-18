package cn.edu.tsu.blog.model;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "subscribe")
public class Subscribe {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "`uid`")
    private Integer uid;

    /**
     * 关注人ID
     */
    @Column(name = "pid")
    private Integer pid;
}