package cn.edu.tsu.blog.controller;

import cn.edu.tsu.blog.commons.code.MsgCode;
import cn.edu.tsu.blog.commons.dto.ResponseResult;
import cn.edu.tsu.blog.model.Comment;
import cn.edu.tsu.blog.model.Subscribe;
import cn.edu.tsu.blog.service.SubscribeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("subscribe")
public class SubscribeController {

    @Autowired
    private SubscribeService subscribeService;

    @PostMapping("create")
    public ResponseResult<?> create(@RequestBody Subscribe subscribe){
        int result = subscribeService.insert(subscribe);
        if(result>0){
            return new ResponseResult<>(MsgCode.SUCCESS,"success",result);
        }else {
            return new ResponseResult<>(MsgCode.FAILED,"failed",result);
        }
    }

    @GetMapping("delete/{id}")
    public ResponseResult<?> delete(@PathVariable Integer id){
        int result = subscribeService.delete(id);
        if(result>0){
            return new ResponseResult<>(MsgCode.SUCCESS,"success",result);
        }else {
            return new ResponseResult<>(MsgCode.FAILED,"failed",result);
        }
    }

    @PostMapping("update}")
    public ResponseResult<?> update(Subscribe subscribe){
        int result = subscribeService.update(subscribe);
        if(result>0){
            return new ResponseResult<>(MsgCode.SUCCESS,"success",result);
        }else {
            return new ResponseResult<>(MsgCode.FAILED,"failed",result);
        }
    }

    @PostMapping("list")
    public ResponseResult<PageInfo<Subscribe>> getPageList(@RequestParam(required = false) String keyword ,
                                                         @RequestParam(defaultValue = "1")Integer pageNum,
                                                         @RequestParam(defaultValue = "10") Integer pageSize){

        PageInfo<Subscribe> pageInfo = subscribeService.selectByPage(pageNum, pageSize, keyword);

        return new ResponseResult<>(MsgCode.SUCCESS,"success",pageInfo);

    }
}
