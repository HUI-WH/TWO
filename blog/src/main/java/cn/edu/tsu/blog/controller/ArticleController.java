package cn.edu.tsu.blog.controller;

import cn.edu.tsu.blog.commons.code.MsgCode;
import cn.edu.tsu.blog.commons.dto.ArticleDto;
import cn.edu.tsu.blog.commons.dto.ArticleWithTreeComment;
import cn.edu.tsu.blog.commons.dto.ResponseResult;
import cn.edu.tsu.blog.dao.ArticleDtoDao;
import cn.edu.tsu.blog.mapper.ArticleMapper;
import cn.edu.tsu.blog.model.Article;
import cn.edu.tsu.blog.service.ArticleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.applet.resources.MsgAppletViewer;

@CrossOrigin
@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping("create")
    public ResponseResult<?> createArticle(@RequestBody Article article){
        int result = articleService.insert(article);
        if(result>0){
            return new ResponseResult<>(MsgCode.SUCCESS,"success",result);
        }else {
            return new ResponseResult<>(MsgCode.FAILED,"failed",result);
        }
    }

    @GetMapping("delete/{id}")
    public ResponseResult<?> deleteArticle(@PathVariable Integer id){
        int result = articleService.delete(id);
        if(result>0){
            return new ResponseResult<>(MsgCode.SUCCESS,"success",result);
        }else {
            return new ResponseResult<>(MsgCode.FAILED,"failed",result);
        }
    }

    @PostMapping("update}")
    public ResponseResult<?> updateArticle(Article article){
        int result = articleService.update(article);
        if(result>0){
            return new ResponseResult<>(MsgCode.SUCCESS,"success",result);
        }else {
            return new ResponseResult<>(MsgCode.FAILED,"failed",result);
        }
    }

    @PostMapping("list")
    public ResponseResult<PageInfo<Article>> getPageList(@RequestParam(required = false) String keyword,
                                                         @RequestParam(required = false) Integer classId,
                                                         @RequestParam(required = false) Integer authorId,
                                                         @RequestParam(defaultValue = "1")Integer pageNum,
                                                         @RequestParam(defaultValue = "10") Integer pageSize){

        PageInfo<Article> articlePageInfo = articleService.selectByPage(pageNum, pageSize, keyword,classId,authorId);

        return new ResponseResult<>(MsgCode.SUCCESS,"success",articlePageInfo);

    }

    @GetMapping("get/{id}")
    public ResponseResult<ArticleWithTreeComment> getArticle(@PathVariable Integer id){
        ArticleWithTreeComment articleWithAllInfo = articleService.getArticleWithAllDetailInfo(id);
        return new ResponseResult<>(MsgCode.SUCCESS,"查询成功",articleWithAllInfo);
    }

}
