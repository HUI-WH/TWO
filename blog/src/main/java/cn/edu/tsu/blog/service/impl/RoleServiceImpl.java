package cn.edu.tsu.blog.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.edu.tsu.blog.mapper.RoleMapper;
import cn.edu.tsu.blog.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService{

    @Resource
    private RoleMapper roleMapper;

}
