package cn.edu.tsu.blog.controller;

import cn.edu.tsu.blog.commons.code.MsgCode;
import cn.edu.tsu.blog.commons.dto.ResponseResult;
import cn.edu.tsu.blog.model.BlogClass;
import cn.edu.tsu.blog.service.BlogClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/blogClass")
public class BlogClassController {

    @Autowired
    private BlogClassService blogClassService;

    @GetMapping("/list")
    public ResponseResult<List<BlogClass>> getList(){
        return new ResponseResult<>(MsgCode.SUCCESS,"查询所有博客类别",blogClassService.selectAll());
    }

}
