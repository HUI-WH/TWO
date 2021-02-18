package cn.edu.tsu.blog.model;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "`label`")
public class Label {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "label_name")
    private String labelName;

    @Column(name = "createtime")
    private Date createtime;

    @Column(name = "label_class")
    private Integer labelClass;

    @Column(name = "pid")
    private Integer pid;
}