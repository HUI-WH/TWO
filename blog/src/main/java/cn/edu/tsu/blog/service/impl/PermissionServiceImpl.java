package cn.edu.tsu.blog.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.edu.tsu.blog.mapper.PermissionMapper;
import cn.edu.tsu.blog.service.PermissionService;
@Service
public class PermissionServiceImpl implements PermissionService{

    @Resource
    private PermissionMapper permissionMapper;

}
