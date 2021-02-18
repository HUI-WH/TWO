package cn.edu.tsu.blog.commons.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = -2389711356230155565L;
    private int code;
    private String msg;
    private T data;

    public ResponseResult() {

    }

    public ResponseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }
    public ResponseResult<T> success(int code, T data) {
        return new ResponseResult<>(code, "success", data);
    }

    public ResponseResult<T> failed(int code, T data) {
        return new ResponseResult<>(code, "failed", data);
    }
}
