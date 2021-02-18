package cn.edu.tsu.blog.mapper;

import cn.edu.tsu.blog.model.User;
import tk.mybatis.mapper.MyMapper;

public interface UserMapper extends MyMapper<User> {

    /**
     * 获得粉丝数量
     * @param id 用户ID
     * @return
     */
    Integer getFansNum(Integer id);

    /**
     * 获取收藏数量
     * @param id
     * @return
     */
    Integer getCollectionNum(Integer id);

    /**\
     * 获取收到的文章评论数量
     * @param id
     * @return
     */
    Integer getCommentNum(Integer id);

    /**
     * 获得关注数量
     * @param id
     * @return
     */
    Integer getFollowNum(Integer id);

    /**
     * 获得文章总阅读量
     * @param id
     * @return
     */
    Integer getReadingNum(Integer id);

    Integer getArticleNum(Integer id);

    Integer getLikesNum(Integer id);

}