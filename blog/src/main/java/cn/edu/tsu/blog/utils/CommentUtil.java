package cn.edu.tsu.blog.utils;

import cn.edu.tsu.blog.commons.dto.TreeCommentDto;
import cn.edu.tsu.blog.dto.TreeComment;
import cn.edu.tsu.blog.model.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentUtil {

    /**
     * 创建树形结构的评论
     *
     * @param commentList 按照pid进行排序的评论列表
     * @return
     */
    public static TreeComment createTreeComment(List<Comment> commentList) {

        if(commentList==null||commentList.size()==0){
            return null;
        }
        TreeComment treeComment = new TreeComment();
        treeComment.setId(0);
        treeComment.setChildren(new ArrayList<>());
        for (Comment comment : commentList) {
            if(comment==null||comment.getPid()==null){
                continue;
            }
            int pid = comment.getPid();
            TreeComment parentComment = findParentComment(pid, treeComment);
            TreeComment newTreeCommentDto = new TreeComment();
            newTreeCommentDto.setId(comment.getId());
            newTreeCommentDto.setInfo(comment);
            newTreeCommentDto.setChildren(new ArrayList<>());
            if (parentComment != null) {
                parentComment.getChildren().add(newTreeCommentDto);
            }
        }

        return treeComment;
    }

    private static TreeComment findParentComment(int id, TreeComment treeCommentDto) {
        if (id == treeCommentDto.getId()) {
            return treeCommentDto;
        } else {
            List<TreeComment> children = treeCommentDto.getChildren();
            for (TreeComment child : children) {
                TreeComment parentComment = findParentComment(id, child);
                if (parentComment != null) {
                    return parentComment;
                }
            }
            return null;
        }

    }
    public static List<Integer> getAllChildrenId(Integer pid,TreeComment treeComment){
        if(pid==null){
            return new ArrayList<>();
        }
        // 目标子树 该树的id=pid
        TreeComment objectTree = findTree(pid, treeComment);

        // TODO 提取所有的评论ID

        List<Integer> allId = getAllId(objectTree);

        return allId;
    }

    private static List<Integer> getAllId(TreeComment treeComment){
        List<Integer> idList = new ArrayList<>();

        if(treeComment!=null){

            idList.add(treeComment.getId());
            if(treeComment.getChildren()!=null&&treeComment.getChildren().size()>0){
                for(TreeComment tree : treeComment.getChildren()){
                    List<Integer> allId = getAllId(tree);
                    if(allId!=null){
                        idList.addAll(allId);
                    }
                }
            }
        }

        return idList;

    }

    private static TreeComment findTree(Integer id,TreeComment treeComment){

        if(id.equals(treeComment.getId())){
            return treeComment;
        }else {
            if(treeComment.getChildren()!=null){
                for( TreeComment comment : treeComment.getChildren() ){
                    TreeComment tree = findTree(id, comment);
                    if(tree!=null){
                        // 目标子树已找到
                        return tree;
                    }
                }
            }
            return null;
        }


    }
}
