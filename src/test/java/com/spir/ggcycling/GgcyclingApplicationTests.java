package com.spir.ggcycling;

import com.spir.ggcycling.bean.Comment;
import com.spir.ggcycling.bean.News;
import com.spir.ggcycling.bean.User;
import com.spir.ggcycling.dao.CommentMapper;
import com.spir.ggcycling.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GgcyclingApplicationTests {


    @Autowired
    UserService userService;

    @Autowired
    CommentMapper commentMapper;
    @Test
    public void contextLoads() {
        List<News> newsList = userService.queryNews();
        LoggerFactory.getLogger(getClass()).error("newList" + newsList);
    }

    @Test
    public void registerUser(){
        User user = new User();
        user.setName("12222");
        user.setPassword("13123");
        boolean b = userService.addUser(user);
        LoggerFactory.getLogger(getClass()).error("user : " + user);
    }

    /*
    测试逆向工程中生成的text类型字段为继承
     */
    /*
    结果，content虽然是继承，但是也可以查出来

     */
    @Test
    public void CommentTest(){
        Comment comment = commentMapper.selectByPrimaryKey(1);
        LoggerFactory.getLogger(getClass()).info("comment = " + comment);
    }

    @Test
    public void queryAllComments(){
        List<Comment> comments = commentMapper.queryAllComments(1, 1);
        LoggerFactory.getLogger(getClass()).info("comment = " + comments);
    }

}
