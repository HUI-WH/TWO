package cn.edu.tsu.blog.mapper;

import cn.edu.tsu.blog.model.Permission;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface PermissionMapper extends MyMapper<Permission> {
    List<Permission> getPermissionList(Integer id);
    List<Permission> getUserPermissionList(Integer id);
}