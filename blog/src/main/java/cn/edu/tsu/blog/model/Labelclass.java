package cn.edu.tsu.blog.model;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "labelclass")
public class Labelclass {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 标签类别名
     */
    @Column(name = "`name`")
    private String name;
}