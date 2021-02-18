package cn.edu.tsu.blog.service.impl;

import cn.edu.tsu.blog.commons.dto.TreeCommentDto;
import cn.edu.tsu.blog.dto.TreeComment;
import cn.edu.tsu.blog.model.Article;
import cn.edu.tsu.blog.model.Comment;
import cn.edu.tsu.blog.model.User;
import cn.edu.tsu.blog.service.ArticleService;
import cn.edu.tsu.blog.service.UserService;
import cn.edu.tsu.blog.utils.CommentUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.edu.tsu.blog.mapper.CommentMapper;
import cn.edu.tsu.blog.service.CommentService;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private UserService userService;
    @Resource
    private ArticleService articleService;


    @Override
    public Integer insert(Comment comment) {
        Comment comment1 = initComment(comment);
        boolean b = validComment(comment);
        if(b){
            return commentMapper.insert(comment1);
        }
        return 0;
    }

    @Override
    public Integer delete(Integer id) {
        return commentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer update(Comment comment) {
        boolean b = validComment(comment);
        if(b){
            return commentMapper.updateByPrimaryKeySelective(comment);
        }
        return 0;
    }

    @Override
    public Comment getById(Integer id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Comment> selectByPage(int pageNum, int pageSize, String keyword) {

        PageHelper.startPage(pageNum, pageSize);

        List<Comment> comments = commentMapper.selectAll();

        return new PageInfo<>(comments);
    }

    @Override
    public List<Comment> selectByAid(Integer aid) {
        if(aid!=null){
            Example e = new Example(Comment.class);
            e.orderBy("pid");
            e.createCriteria().andEqualTo("aid",aid);
            List<Comment> comments = commentMapper.selectByExample(e);
            return comments;
        }



        return new ArrayList<>();
    }

    @Override
    public Integer deleteByList(List<Integer> idList) {
        if(idList==null||idList.size()==0){
            return 0;
        }
        Example e = new Example(Comment.class);
        e.createCriteria().andIn("id",idList);
        return commentMapper.deleteByExample(e);
    }


    private Comment initComment(Comment comment){
        comment.setCreateTime(new Date());
        comment.setLikes(0);
        return comment;
    }


    public Integer delete(Integer cid,Integer uid,Integer aid){

        if (uid != null && cid != null && aid != null) {
            Example example = new Example(Comment.class);
            example.createCriteria().andEqualTo("id", cid).andEqualTo("aid", aid);
            Comment comment = commentMapper.selectOneByExample(example);
            if (comment != null && comment.getUid().equals(uid)) {
                //开始删除
                // 获取评论的所有子评论一同删除

                List<Comment> comments = selectByAid(aid);

                TreeComment treeComment = CommentUtil.createTreeComment(comments);
                List<Integer> allChildrenId = CommentUtil.getAllChildrenId(cid, treeComment);
                return deleteByList(allChildrenId);
            }
        }
        return 0;

    }





    private boolean validComment(Comment comment){
        if(comment==null||comment.getAid()==null||comment.getPid()==null||comment.getUid()==null||comment.getLikes()==null||comment.getArticleScore()==null){
            return false;
        }
        if(comment.getLikes()<0||comment.getUid()<0||comment.getPid()<0||comment.getAid()<0||comment.getArticleScore()<0){
            return false;
        }
        if(comment.getContent()==null|| comment.getContent().trim().equals("")){
            return false;
        }
        User byID = userService.getByID(comment.getUid());
        Article byID1 = articleService.getByID(comment.getAid());
        Comment byId = getById(comment.getPid());
        if(byID==null||byID1==null){
            return false;
        }
        if(byId==null){
            return comment.getPid() == 0;
        }else {
            return byId.getAid().equals(comment.getAid());
        }

    }
}

