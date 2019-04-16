package com.spir.ggcycling.service;

import com.spir.ggcycling.bean.Comment;

import java.util.List;

/**
 * created by spir
 * Date2019/4/14 Time 16:38
 */
public interface CommentService {

    List<Comment> queryAllComments(int newsId, int news);


    void addCommentForNewsId(int newsId, String content, int news,int userId);
}
