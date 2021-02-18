package cn.edu.tsu.blog.commons.dto;

import com.sun.xml.internal.ws.developer.Serialization;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginParams implements Serializable {

    private String username;
    private String password;
}
