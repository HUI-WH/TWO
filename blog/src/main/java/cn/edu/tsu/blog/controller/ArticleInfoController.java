package cn.edu.tsu.blog.controller;

import cn.edu.tsu.blog.commons.code.MsgCode;
import cn.edu.tsu.blog.commons.dto.ResponseResult;
import cn.edu.tsu.blog.model.Article;
import cn.edu.tsu.blog.model.Articleinfo;
import cn.edu.tsu.blog.service.ArticleinfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("articleInfo")
public class ArticleInfoController {

    @Autowired
    private ArticleinfoService articleinfoService;

    @PostMapping("create")
    public ResponseResult<?> create(@RequestBody Articleinfo articleinfo){
        int result = articleinfoService.insert(articleinfo);
        if(result>0){
            return new ResponseResult<>(MsgCode.SUCCESS,"success",result);
        }else {
            return new ResponseResult<>(MsgCode.FAILED,"failed",result);
        }
    }

    @GetMapping("delete/{id}")
    public ResponseResult<?> delete(@PathVariable Integer id){
        int result = articleinfoService.delete(id);
        if(result>0){
            return new ResponseResult<>(MsgCode.SUCCESS,"success",result);
        }else {
            return new ResponseResult<>(MsgCode.FAILED,"failed",result);
        }
    }

    @PostMapping("update}")
    public ResponseResult<?> update(Articleinfo articleinfo){
        int result = articleinfoService.update(articleinfo);
        if(result>0){
            return new ResponseResult<>(MsgCode.SUCCESS,"success",result);
        }else {
            return new ResponseResult<>(MsgCode.FAILED,"failed",result);
        }
    }

    @PostMapping("list")
    public ResponseResult<PageInfo<Articleinfo>> getPageList(@RequestParam(required = false) String keyword ,
                                                         @RequestParam(defaultValue = "1")Integer pageNum,
                                                         @RequestParam(defaultValue = "10") Integer pageSize){

        PageInfo<Articleinfo> articlePageInfo = articleinfoService.selectByPage(pageNum, pageSize, keyword);

        return new ResponseResult<>(MsgCode.SUCCESS,"success",articlePageInfo);

    }

    @GetMapping("get/{id}")
    public ResponseResult<Articleinfo> getInfo(@PathVariable Integer id){

        if(id!=null){
            Articleinfo infoByAid = articleinfoService.getInfoByAid(id);
            return new ResponseResult<>(MsgCode.SUCCESS,"success",infoByAid);
        }
        return new ResponseResult<>(MsgCode.FAILED,"failed");


    }

}
