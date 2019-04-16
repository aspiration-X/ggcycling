package com.spir.ggcycling.service.Impl;

import com.spir.ggcycling.bean.Comment;
import com.spir.ggcycling.dao.CommentMapper;
import com.spir.ggcycling.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * created by spir
 * Date2019/4/14 Time 16:38
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Override
    public List<Comment> queryAllComments(int newsId, int news) {
        return commentMapper.queryAllComments(newsId,news);

    }

    @Override
    public void addCommentForNewsId(int newsId, String content, int news,int userId) {
        commentMapper.addCommentForNewsId(newsId,content,news,new Date(),userId);
    }


}
