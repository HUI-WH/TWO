package cn.edu.tsu.blog.controller;

import cn.edu.tsu.blog.commons.code.MsgCode;
import cn.edu.tsu.blog.commons.dto.ResponseResult;
import cn.edu.tsu.blog.model.Articleinfo;
import cn.edu.tsu.blog.model.Comment;
import cn.edu.tsu.blog.service.CommentService;
import cn.edu.tsu.blog.utils.CommentUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("create")
    public ResponseResult<?> create(@RequestBody Comment comment){

        int result = commentService.insert(comment);
        if(result>0){
            return new ResponseResult<>(MsgCode.SUCCESS,"success",result);
        }else {
            return new ResponseResult<>(MsgCode.FAILED,"failed",result);
        }
    }

    @GetMapping("delete/{id}")
    public ResponseResult<?> delete(@PathVariable Integer id){
        int result = commentService.delete(id);
        if(result>0){
            return new ResponseResult<>(MsgCode.SUCCESS,"success",result);
        }else {
            return new ResponseResult<>(MsgCode.FAILED,"failed",result);
        }
    }

    @PostMapping("delete")
    public ResponseResult<?> delete(Integer uid,Integer cid,Integer aid){

        int result = commentService.delete(cid, uid, aid);
        if(result>0){
            return new ResponseResult<>(MsgCode.SUCCESS,"success",result);
        }else {
            return new ResponseResult<>(MsgCode.FAILED,"failed",result);
        }
    }


    @PostMapping("update}")
    public ResponseResult<?> update(Comment comment){
        int result = commentService.update(comment);
        if(result>0){
            return new ResponseResult<>(MsgCode.SUCCESS,"success",result);
        }else {
            return new ResponseResult<>(MsgCode.FAILED,"failed",result);
        }
    }

    @PostMapping("list")
    public ResponseResult<PageInfo<Comment>> getPageList(@RequestParam(required = false) String keyword ,
                                                             @RequestParam(defaultValue = "1")Integer pageNum,
                                                             @RequestParam(defaultValue = "10") Integer pageSize){

        PageInfo<Comment> pageInfo = commentService.selectByPage(pageNum, pageSize, keyword);

        return new ResponseResult<>(MsgCode.SUCCESS,"success",pageInfo);

    }

}
