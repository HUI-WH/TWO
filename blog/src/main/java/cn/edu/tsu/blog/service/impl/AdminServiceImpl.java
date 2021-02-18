package cn.edu.tsu.blog.service.impl;

import cn.edu.tsu.blog.commons.code.MsgCode;
import cn.edu.tsu.blog.commons.dto.AdminUserDetails;
import cn.edu.tsu.blog.mapper.PermissionMapper;
import cn.edu.tsu.blog.model.Admin;
import cn.edu.tsu.blog.model.Permission;
import cn.edu.tsu.blog.service.AdminService;
import cn.edu.tsu.blog.utils.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.edu.tsu.blog.mapper.AdminMapper;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private PermissionMapper permissionMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Override
    public Admin selectById(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public Admin selectByCode(String code) {
        if(StringUtils.isEmpty(code)){
            return null;
        }
        Example e = new Example(Admin.class);
        e.createCriteria().andEqualTo("code",code);
        return adminMapper.selectOneByExample(e);
    }

    @Override
    public String login(Admin admin) {

        String token = null;

        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByCode(admin.getCode());
            if(!passwordEncoder.matches(admin.getPassword(),userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            if(!userDetails.isEnabled()){
                throw new BadCredentialsException("帐号已被禁用");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
//            updateLoginTimeByUsername(username);
//            insertLoginLog(username);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;

    }

    @Override
    public List<Permission> getPermissionList(Integer id) {
        return permissionMapper.getPermissionList(id);
    }

    @Override
    public UserDetails loadUserByCode(String code) {

        Admin admin = selectByCode(code);
        if(admin!=null){
            List<Permission> permissionList = permissionMapper.getPermissionList(admin.getId());
            return  new AdminUserDetails(admin,permissionList);
        }

        throw new UsernameNotFoundException("用户名或密码错误");
    }

    /**
     * 验证密码是否正确
     *
     * @param pwd1 密码1
     * @param pwd2 密码2
     * @return {@code Boolean}
     */
    private Boolean verifyPassword(String pwd1, String pwd2) {
        if (!StringUtils.isEmpty(pwd1) && !StringUtils.isEmpty(pwd2)) {
            return pwd1.equals(pwd2);
        }
        return false;
    }
}



