package cn.edu.tsu.blog.controller;

import cn.edu.tsu.blog.commons.code.MsgCode;
import cn.edu.tsu.blog.commons.code.RespCode;
import cn.edu.tsu.blog.commons.dto.LoginParams;
import cn.edu.tsu.blog.commons.dto.ResponseResult;
import cn.edu.tsu.blog.commons.dto.UserDetailInfoDto;
import cn.edu.tsu.blog.model.Admin;
import cn.edu.tsu.blog.model.User;
import cn.edu.tsu.blog.service.UserService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    private final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @PostMapping("login")
    public ResponseResult<?> login(@RequestBody LoginParams params) {

        User user = new User();
        user.setCode(params.getUsername());
        user.setPassword(params.getPassword());
        String token = userService.login(user);
        if (token == null) {
            return new ResponseResult<>(MsgCode.PWD_OR_CODE_ERROR,"用户名或密码错误",null);
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        LOGGER.info("登陆成功");


        // TODO 修改最后登录时间
        return new ResponseResult<>(MsgCode.SUCCESS,"登陆成功",tokenMap);
//
//        String token = userService.login(user);
//        if (code == MsgCode.SUCCESS) {
//            return new ResponseResult<>(RespCode.OK, "登陆成功", null);
//        }
//        return new ResponseResult<>(RespCode.FAILED, "登陆失败", code);
    }

    @PostMapping("reg")
    public ResponseResult<?> reg(@RequestBody User user) {
        int reg = userService.reg(user);
        if(MsgCode.SUCCESS==reg){
            return new ResponseResult<>(RespCode.OK,"注册成功");
        }
        return new ResponseResult<>(RespCode.FAILED,"注册失败",reg);
    }

    @GetMapping("list")
    public ResponseResult<?> getPageList(@RequestParam(required = false) String keyword ,
                                         @RequestParam(defaultValue = "1")Integer pageNum,
                                         @RequestParam(defaultValue = "3") Integer pageSize){

        PageInfo<User> pageInfo = userService.selectPageList(pageNum, pageSize, keyword);

        return new ResponseResult<>(MsgCode.SUCCESS,"查询成功",pageInfo);
    }

    @PostMapping("/update")
    public ResponseResult<Integer> updateUser(Principal principal,@RequestBody User user){

        String code = principal.getName();
        User pUser = userService.selectByCode(code);
        // 判断数据
        if (user == null||pUser==null) {
            return new ResponseResult<>(MsgCode.SUCCESS,"修改失败",0);
        }

        // 判断是否是当前用户修改自己的个人信息
        if(!user.getId().equals(pUser.getId())||!user.getCode().equals(pUser.getCode())){
            return new ResponseResult<>(MsgCode.SUCCESS,"修改失败",0);
        }else {
            int i = userService.updateUser(user);
            if(i==0){
                return new ResponseResult<>(MsgCode.SUCCESS,"修改失败",i);
            }
            return new ResponseResult<>(MsgCode.SUCCESS,"修改成功",i);
        }

    }


    @GetMapping("/delete/{id}")
    public ResponseResult<?> deleteUser(@PathVariable Integer id){
        int i = userService.deleteUser(id);
        if(i==0){
            return new ResponseResult<>(MsgCode.SUCCESS,"删除失败",i);
        }
        return new ResponseResult<>(MsgCode.SUCCESS,"删除成功",i);
    }
    @RequestMapping("info")
    public ResponseResult<Map<String,Object>> getInfo(Principal principal){

        if(principal==null){
            return new ResponseResult<>(MsgCode.UNAUTHORIZED,"yes",null);
        }
        String code = principal.getName();
        User user = userService.selectByCode(code);
        if(user!=null){
            Map<String, Object> data = new HashMap<>();
            data.put("name", user.getName());
            data.put("avatar",user.getIcon());
            data.put("id",user.getId());
            return new ResponseResult<>(MsgCode.SUCCESS,"yes",data);
        }else {
            return new ResponseResult<>(MsgCode.SUCCESS,"yes",null);
        }

    }


    @GetMapping("infos")
    public ResponseResult<?> getDetailInfo(Principal principal){
        if(principal==null){
            return new ResponseResult<>(MsgCode.UNAUTHORIZED,"yes",null);
        }
        String code = principal.getName();
        User user = userService.selectByCode(code);
        return new ResponseResult<>(20000,"yes",user);
    }

    @GetMapping("logout")
    public ResponseResult<?> logout(){
        return new ResponseResult<>(MsgCode.SUCCESS,"退出登录");
    }

    @GetMapping("detailInfo/{id}")
    public ResponseResult<?> getUserDetailInfo(@PathVariable Integer id){
        UserDetailInfoDto userDetailInfo = userService.getUserDetailInfo(id);

        return new ResponseResult<>(MsgCode.SUCCESS,"查询成功",userDetailInfo) ;
    }

}
