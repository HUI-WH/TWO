package cn.edu.tsu.blog.service;

import cn.edu.tsu.blog.commons.dto.UserDetailInfoDto;
import cn.edu.tsu.blog.model.User;
import com.github.pagehelper.PageInfo;

public interface UserService {

    /**
     * 通过用户主键获取用户信息
     * @param userId 主键
     * @return {@link User}
     */
    User getByID(Integer userId);

    /**
     * 通过用户账号获取用户信息
     * @param code 用户账号
     * @return {@link User}
     */
    User selectByCode(String code);

    /**
     * 用户登录
     * @param user {@link User} 要包含用户账号和用户密码
     * @return {@code int} 1表示成功 其他失败
     */
    String login(User user);

    /**
     * 用户注册
     * @param user {@link User} 用户信息{账号、密码、名称}
     * @return {@code int}
     */
    int reg(User user);

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<User> selectPageList(Integer pageNum,Integer pageSize,String keyword);

    /**
     * 更新用户
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 删除用户
     * @param id
     * @return
     */
    int deleteUser(Integer id);

    UserDetailInfoDto getUserDetailInfo(Integer id);
}



