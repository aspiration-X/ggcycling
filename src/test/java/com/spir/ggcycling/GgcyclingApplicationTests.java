package com.spir.ggcycling;

import com.spir.ggcycling.bean.News;
import com.spir.ggcycling.bean.User;
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

}
