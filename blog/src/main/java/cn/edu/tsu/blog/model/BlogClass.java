package cn.edu.tsu.blog.model;

import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "blog_class")
public class BlogClass {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "`name`")
    private String name;
}