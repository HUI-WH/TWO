package cn.edu.tsu.blog.commons.code;

public class MsgCode {
    /**
     * 成功
     */
    public static final int SUCCESS = 20000;

    /**
     * 失败
     */
    public static final int FAILED=50000;
    /**
     * 密码错误
     */
    public static final int PASSWORD_ERROR = 2;
    /**
     * 用户名错误
     */
    public static final int CODE_ERROR = 3;
    /**
     * 用户名或密码错误
     */
    public static final int PWD_OR_CODE_ERROR = 4;

    /**
     * 账号已存在
     */
    public static final int CODE_EXISTED=5;

    /**
     * 信息错误
     */
    public static final int ERROR=6;

    /**
     * 未登录或token失效
     */
    public static final int UNAUTHORIZED=7;

    /**
     * 无权限
     */
    public static final int FORBIDDEN=8;

}
