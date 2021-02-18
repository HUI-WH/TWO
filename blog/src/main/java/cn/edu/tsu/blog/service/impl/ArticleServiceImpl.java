package cn.edu.tsu.blog.service.impl;
import cn.edu.tsu.blog.dto.CommentDetailDto;
import cn.edu.tsu.blog.dto.CommentDetailTreeDto;

import java.util.Date;

import cn.edu.tsu.blog.commons.dto.ArticleDto;
import cn.edu.tsu.blog.commons.dto.ArticleWithTreeComment;
import cn.edu.tsu.blog.commons.dto.CommentDto;
import cn.edu.tsu.blog.commons.dto.TreeCommentDto;
import cn.edu.tsu.blog.dao.ArticleDtoDao;
import cn.edu.tsu.blog.model.Article;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.edu.tsu.blog.mapper.ArticleMapper;
import cn.edu.tsu.blog.service.ArticleService;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private ArticleDtoDao articleDtoDao;

    @Override
    public Article getByID(Integer id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(Article article) {
        Article init = init(article);
        if(valid(init)){
            return articleMapper.insert(init);
        }else {

            return 0;
        }
    }

    @Override
    public int delete(Integer id) {
        return articleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Article article) {
        return articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    public PageInfo<Article> selectByPage(int pageNum, int pageSize, String keyword,Integer classId,Integer authorId) {
        PageHelper.startPage(pageNum, pageSize);

        Example e = new Example(Article.class);
        if (!StringUtils.isEmpty(keyword)) {
            e.createCriteria().andLike("title", "%" + keyword + "%");
        }
        if(classId!=null){
            e.and().andEqualTo("cid",classId);
        }
        if(authorId!=null){
            e.and().andEqualTo("authorId",authorId);
        }
        List<Article> articles = articleMapper.selectByExample(e);

        return new PageInfo<>(articles);
    }

    @Override
    public ArticleWithTreeComment getArticleWithAllInfo(int id) {
        ArticleDto articleDto = articleDtoDao.get(id);

        List<CommentDto> commentList = articleDto.getCommentList();

        for(CommentDto commentDto:commentList){
            Date createTime = commentDto.getCreateTime();
            Date now =new Date();
            String s = calcAfterTime(createTime, now);
            commentDto.setAfterTime(s);
        }



        TreeCommentDto treeCommentDto = createTreeComment(commentList);

        ArticleWithTreeComment articleWithTreeComment = new ArticleWithTreeComment();
        articleWithTreeComment.setId(articleDto.getId());
        articleWithTreeComment.setTitle(articleDto.getTitle());
        articleWithTreeComment.setAuthorId(articleDto.getAuthorId());
        articleWithTreeComment.setAuthorName(articleDto.getAuthorName());
        articleWithTreeComment.setDescribe(articleDto.getDescribe());
        articleWithTreeComment.setTitleImg(articleDto.getTitleImg());
        articleWithTreeComment.setContent(articleDto.getContent());
        articleWithTreeComment.setArticleinfo(articleDto.getArticleinfo());
        articleWithTreeComment.setCreateTime(articleDto.getCreateTime());
        articleWithTreeComment.setTreeCommentDto(treeCommentDto);
        articleWithTreeComment.setLabels(articleDto.getLabels());

        return articleWithTreeComment;
    }

    @Override
    public ArticleWithTreeComment getArticleWithAllDetailInfo(int id) {

        ArticleDto articleDto = articleDtoDao.get(id);
        if(articleDto==null){
            return null;
        }
        List<CommentDto> commentList = articleDto.getCommentList();
        ArticleWithTreeComment articleWithTreeComment = new ArticleWithTreeComment();
        if(commentList !=null){
            for(CommentDto commentDto:commentList){
                Date createTime = commentDto.getCreateTime();
                Date now =new Date();
                String s = calcAfterTime(createTime, now);
                commentDto.setAfterTime(s);
            }



            TreeCommentDto treeCommentDto = createTreeComment(commentList);

            List<CommentDetailTreeDto> c = convertTree(treeCommentDto);
            articleWithTreeComment.setDetailTreeDto(c);
        }



        articleWithTreeComment.setId(articleDto.getId());
        articleWithTreeComment.setTitle(articleDto.getTitle());
        articleWithTreeComment.setAuthorId(articleDto.getAuthorId());
        articleWithTreeComment.setAuthorName(articleDto.getAuthorName());
        articleWithTreeComment.setDescribe(articleDto.getDescribe());
        articleWithTreeComment.setTitleImg(articleDto.getTitleImg());
        articleWithTreeComment.setContent(articleDto.getContent());
        articleWithTreeComment.setArticleinfo(articleDto.getArticleinfo());
        articleWithTreeComment.setCreateTime(articleDto.getCreateTime());
        articleWithTreeComment.setLabels(articleDto.getLabels());

        return articleWithTreeComment;

    }

    /**
     * 创建树形结构的评论
     *
     * @param commentDtoList 按照pid进行排序的评论列表
     * @return
     */
    public TreeCommentDto createTreeComment(List<CommentDto> commentDtoList) {

        if(commentDtoList==null||commentDtoList.size()==0){
            return null;
        }
        TreeCommentDto treeCommentDto = new TreeCommentDto();
        treeCommentDto.setId(0);
        treeCommentDto.setChildren(new ArrayList<>());
        for (CommentDto commentDto : commentDtoList) {
            if(commentDto==null||commentDto.getPid()==null){
                continue;
            }
            int pid = commentDto.getPid();
            TreeCommentDto parentComment = findParentComment(pid, treeCommentDto);
            TreeCommentDto newTreeCommentDto = new TreeCommentDto();
            newTreeCommentDto.setId(commentDto.getId());
            newTreeCommentDto.setInfo(commentDto);
            newTreeCommentDto.setChildren(new ArrayList<>());
            if (parentComment != null) {
                parentComment.getChildren().add(newTreeCommentDto);
            }
        }

        return treeCommentDto;
    }

    private TreeCommentDto findParentComment(int id, TreeCommentDto treeCommentDto) {

        int id1 = treeCommentDto.getId();
        if (id == id1) {
            return treeCommentDto;
        } else {
            List<TreeCommentDto> children = treeCommentDto.getChildren();
            for (TreeCommentDto child : children) {
                TreeCommentDto parentComment = findParentComment(id, child);
                if (parentComment != null) {
                    return parentComment;
                }
            }
            return null;
        }

    }


    private List<CommentDetailTreeDto> convertTree(TreeCommentDto treeCommentDto){

        List<CommentDetailTreeDto> detailTreeDtos=new ArrayList<>();
        List<TreeCommentDto> children = treeCommentDto.getChildren();

        if(treeCommentDto ==null){
            return null;
        }else{
            for(TreeCommentDto treeC:children){
                CommentDetailTreeDto treeDto = new CommentDetailTreeDto();
                CommentDetailDto commentDetailDto = new CommentDetailDto();
                CommentDto info = treeC.getInfo();
                commentDetailDto.setId(info.getId());
                commentDetailDto.setUid(info.getUid());
                commentDetailDto.setAid(info.getAid());
                commentDetailDto.setPid(info.getPid());
                commentDetailDto.setContent(info.getContent());
                commentDetailDto.setPUid(null);
                commentDetailDto.setPUName(null);
                commentDetailDto.setUName(info.getUName());
                commentDetailDto.setUIcon(info.getUIcon());
                commentDetailDto.setCommentTime(info.getCreateTime());
                commentDetailDto.setLikes(info.getLikes());
                commentDetailDto.setScore(info.getArticleScore());
                commentDetailDto.setAfterTime(info.getAfterTime());

                treeDto.setId(treeC.getId());
                treeDto.setInfo(commentDetailDto);

                List<CommentDetailDto> allChildrenComment = getAllChildrenComment(treeC);
                treeDto.setChildren(allChildrenComment);
                detailTreeDtos.add(treeDto);
            }
        }

        return detailTreeDtos;
    }

    private List<CommentDetailDto> getAllChildrenComment(TreeCommentDto treeCommentDto){
        List<CommentDetailDto> list = new ArrayList<>();
        if(treeCommentDto ==null|| treeCommentDto.getChildren()==null|| treeCommentDto.getChildren().size()==0){
            return null;
        }else {
            List<TreeCommentDto> children = treeCommentDto.getChildren();
            for(TreeCommentDto tree : children){
                if(tree!=null){
                    CommentDetailDto commentDetailDto = new CommentDetailDto();
                    CommentDto info = tree.getInfo();
                    commentDetailDto.setId(info.getId());
                    commentDetailDto.setUid(info.getUid());
                    commentDetailDto.setUName(info.getUName());
                    commentDetailDto.setContent(info.getContent());
                    commentDetailDto.setAid(info.getAid());
                    commentDetailDto.setPid(info.getPid());
                    commentDetailDto.setPUid(treeCommentDto.getInfo().getUid());
                    commentDetailDto.setPUName(treeCommentDto.getInfo().getUName());
                    commentDetailDto.setUIcon(info.getUIcon());
                    commentDetailDto.setCommentTime(info.getCreateTime());
                    commentDetailDto.setLikes(info.getLikes());
                    commentDetailDto.setScore(info.getArticleScore());
                    commentDetailDto.setAfterTime(info.getAfterTime());
                    list.add(commentDetailDto);
                    List<CommentDetailDto> allChildrenComment = getAllChildrenComment(tree);
                    if(allChildrenComment!=null&&allChildrenComment.size()>0){
                        list.addAll(allChildrenComment);
                    }

                }
            }
            return list;
        }
    }


    /**
     * 计算评论在多长时间之前
     *
     * @return "xx天前"
     */
    private String calcAfterTime(Date oldTime, Date newTime) {
        if(oldTime==null|| newTime==null){
            return "";
        }

        long cha = newTime.getTime() - oldTime.getTime();
        long s = cha / 1000;
        long m = s / 60;
        long h = m / 60;
        long d = h / 24;
        long yue = d / 30;
        long y = yue / 12;

        if (y != 0) {
            return (y + "年前");
        } else if (yue != 0) {
            return (yue + "月前");
        } else if (d != 0) {
            return (d + "天前");
        } else if (h != 0) {
            return (h + "小时前");
        } else if (m != 0) {
            return (m + "分钟前");
        } else {
            return (s + "秒前");
        }
    }

    private boolean valid(Article article){
        if(article.getAuthorId()==null||article.getCid()==null||article.getContent()==null||article.getContentType()==null||article.getDescribe()==null||article.getTitle()==null){
            return false;
        }else if(StringUtils.isEmpty(article.getContent().trim())||StringUtils.isEmpty(article.getTitle().trim())||StringUtils.isEmpty(article.getDescribe().trim())){
            return false;
        }
        article.setTitle(article.getTitle().trim());
        article.setContent(article.getContent().trim());
        article.setDescribe(article.getDescribe().trim());
        return true;
    }

    private Article init(Article article){
        article.setCreateTime(new Date());
        article.setContentType(0);
        return  article;
    }
}

