package cn.edu.tsu.blog.service;

import cn.edu.tsu.blog.model.Admin;
import cn.edu.tsu.blog.model.Permission;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface AdminService {
    /**
     * 通过主键获取管理员信息
     * @param id {@link Integer} 主键
     * @return {@link Admin} 管理员信息
     */
    Admin selectById(Integer id);

    /**
     * 通过站好获取管理员信息
     * @param code {@code 账号}
     * @return {@link Admin}
     */
    Admin selectByCode(String code);

    /**
     * 登录
     * @param admin {@link Admin} 管理员信息，账号密码
     * @return {@code 状态码 }
     */
    String login(Admin admin);

    /**
     * 获得管理员角色列表
     * @param id
     * @return
     */
    List<Permission> getPermissionList(Integer id);


    UserDetails loadUserByCode(String code);
}



