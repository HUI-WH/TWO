package cn.edu.tsu.blog.service.impl;

import cn.edu.tsu.blog.commons.code.MsgCode;
import cn.edu.tsu.blog.commons.dto.AdminUserDetails;
import cn.edu.tsu.blog.commons.dto.OwnUserDetails;
import cn.edu.tsu.blog.commons.dto.UserDetailInfoDto;
import cn.edu.tsu.blog.mapper.PermissionMapper;
import cn.edu.tsu.blog.model.Admin;
import cn.edu.tsu.blog.model.Permission;
import cn.edu.tsu.blog.model.User;
import cn.edu.tsu.blog.service.UserService;
import cn.edu.tsu.blog.utils.JwtTokenUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import cn.edu.tsu.blog.mapper.UserMapper;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Logger LOGGER= LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    private UserMapper userMapper;



    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public User getByID(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public User selectByCode(String code) {
        Example e = new Example(User.class);
        e.createCriteria().andEqualTo("code", code);
        return userMapper.selectOneByExample(e);
    }


    @Override
    public String login(User user) {

        String token = null;

        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByCode(user.getCode());
            if(!passwordEncoder.matches(user.getPassword(),userDetails.getPassword())){
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

//        if (user != null && !StringUtils.isEmpty(user.getCode()) && !StringUtils.isEmpty(user.getPassword())) {
//            String code = user.getCode();
//            String password = user.getPassword();
//            User user1 = selectByCode(code);
//            if (user1 != null) {
//                if (verifyPassword(password, user1.getPassword())) {
//                    return MsgCode.SUCCESS;
//                }
//                return MsgCode.PASSWORD_ERROR;
//            }
//            return MsgCode.CODE_ERROR;
//        }
//        return MsgCode.PWD_OR_CODE_ERROR;
    }

    @Override
    public int reg(User user) {
        if (user == null) {
            return MsgCode.ERROR;
        }
        if(!StringUtils.isEmpty(user.getCode())&&!StringUtils.isEmpty(user.getPassword())&& !StringUtils.isEmpty(user.getName())){
            User user1 = selectByCode(user.getCode());
            if(user1!=null){
                return MsgCode.CODE_EXISTED;
            } else {
                user.setCreateTime(new Date());
                int i = userMapper.insertSelective(user);
                if(i==1){
                    return MsgCode.SUCCESS;
                }
                return MsgCode.ERROR;
            }
        }
        return MsgCode.ERROR;
    }

    @Override
    public PageInfo<User> selectPageList(Integer pageNum, Integer pageSize,String keyWord) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> users;
         Example e =new Example(User.class);
        if(!StringUtils.isEmpty(keyWord)){
            e.createCriteria().andLike("name","%"+keyWord+"%");
        }
        users = userMapper.selectByExample(e);
        return new PageInfo<>(users);
    }

    @Override
    public int updateUser(User user) {
        if(user!=null){
            User byID = getByID(user.getId());
            if(byID!=null){
                byID.setEmail(user.getEmail());
                byID.setIcon(user.getIcon());
                byID.setName(user.getName());
                return userMapper.updateByPrimaryKeySelective(byID);
            }
        }
        return 0;
    }

    @Override
    public int deleteUser(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public UserDetailInfoDto getUserDetailInfo(Integer id) {
        UserDetailInfoDto dto = new UserDetailInfoDto();
        User user = getByID(id);
        if(user!=null){
            Integer collectionNum = userMapper.getCollectionNum(id);
            Integer readingNum = userMapper.getReadingNum(id);
            Integer commentNum = userMapper.getCommentNum(id);
            Integer fansNum = userMapper.getFansNum(id);
            Integer followNum = userMapper.getFollowNum(id);
            Integer articleNum = userMapper.getArticleNum(id);
            Integer likesNum = userMapper.getLikesNum(id);
            Calendar calendar =Calendar.getInstance();
            calendar.setTime(user.getCreateTime());
            int createYear = calendar.get(Calendar.YEAR);

            Calendar nowDate = Calendar.getInstance();
            nowDate.setTime(new Date());
            int nowYear = nowDate.get(Calendar.YEAR);


            Example e = new Example(User.class);

            dto.setId(id);
            dto.setCode(user.getCode());
            dto.setEmail(user.getEmail());
            dto.setName(user.getName());
            dto.setIcon(user.getIcon());
            dto.setCreateTime(user.getCreateTime());
            dto.setBlogAge(nowYear-createYear);
            dto.setReadingNum(readingNum);
            dto.setFansNum(fansNum);
            dto.setCommentNum(commentNum);
            dto.setCollectionNum(collectionNum);
            dto.setFollowNum(followNum);
            dto.setArticleNum(articleNum);
            dto.setLikesNum(likesNum);
            return dto;
        }else {
            return null;
        }
    }


    UserDetails loadUserByCode(String code){
        User user = selectByCode(code);
        if(user!=null){
            List<Permission> permissionList = permissionMapper.getUserPermissionList(user.getId());
            return  new OwnUserDetails(user,permissionList);
        }

        throw new UsernameNotFoundException("用户名或密码错误");
    }

    /**
     * 密码比较
     * @param pwd1 明文密码
     * @param pwd2 加密密码
     * @return
     */
    private Boolean verifyPassword(String pwd1, String pwd2) {
        if (!StringUtils.isEmpty(pwd1) && !StringUtils.isEmpty(pwd2)) {
            return passwordEncoder.matches(pwd1,pwd2);
        }
        return false;
    }
}



