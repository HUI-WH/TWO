package cn.edu.tsu.blog.controller;

import cn.edu.tsu.blog.commons.code.MsgCode;
import cn.edu.tsu.blog.commons.code.RespCode;
import cn.edu.tsu.blog.commons.dto.LoginParams;
import cn.edu.tsu.blog.commons.dto.ResponseResult;
import cn.edu.tsu.blog.model.Admin;
import cn.edu.tsu.blog.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("admin")
public class AdminController {
    private final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private AdminService adminService;

    @PostMapping("login")
    public ResponseResult<?> login(@RequestBody LoginParams params){
        Admin admin = new Admin();
        admin.setCode(params.getUsername());
        admin.setPassword(params.getPassword());
        String token = adminService.login(admin);
        if (token == null) {
            return new ResponseResult<>(MsgCode.PWD_OR_CODE_ERROR,"用户名或密码错误",null);
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        LOGGER.info("登陆成功");
        return new ResponseResult<>(MsgCode.SUCCESS,"登陆成功",tokenMap);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping("info")
    public ResponseResult<Map<String,Object>> getInfo(){
        Map<String, Object> map = new HashMap<>();
        map.put("name","Tom");
        map.put("avatar","Tom");
        return new ResponseResult<>(20000,"yes",map);
    }

    @GetMapping("logout")
    public ResponseResult<?> logout(){
        return new ResponseResult<>(MsgCode.SUCCESS,"退出登录");
    }

}
