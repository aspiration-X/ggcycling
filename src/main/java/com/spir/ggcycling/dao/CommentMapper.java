package com.spir.ggcycling.dao;

import com.spir.ggcycling.bean.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKeyWithBLOBs(Comment record);

    int updateByPrimaryKey(Comment record);

    /**
     * 查找对应news的comment
     * @param newsId  对应数据表表中entityId
     * @param news  news对应表中EntityType
     * @return
     */
    List<Comment> queryAllComments(@Param("newsId") int newsId, @Param("news") int news);

    void addCommentForNewsId(@Param("entityId") int newsId, @Param("content") String content,
                             @Param("entityType") int news, @Param("createdDate")Date date,
                             @Param("userId") int userId );
}