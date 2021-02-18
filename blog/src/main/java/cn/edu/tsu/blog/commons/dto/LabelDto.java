package cn.edu.tsu.blog.commons.dto;

import cn.edu.tsu.blog.model.Label;
import com.sun.xml.internal.ws.developer.Serialization;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class LabelDto implements Serializable {

    private int id;
    private String name;
    private List<Label> children;

}
