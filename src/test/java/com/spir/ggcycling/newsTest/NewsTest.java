package com.spir.ggcycling.newsTest;

import com.spir.ggcycling.bean.User;
import com.spir.ggcycling.dao.NewsMapper;
import com.spir.ggcycling.service.NewsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * created by spir
 * Date2019/4/16 Time 23:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsTest {


    @Autowired
    NewsService newsService;
    @Autowired
    NewsMapper newsMapper;

    @Test
    public void queryUserByNewsId(){
        User user = newsMapper.queryUserByNewsId(50);
        System.out.println(user);
    }
}
