package com.spir.ggcycling.service.Impl;

import com.spir.ggcycling.bean.News;
import com.spir.ggcycling.bean.User;
import com.spir.ggcycling.dao.NewsMapper;
import com.spir.ggcycling.dao.UserMapper;
import com.spir.ggcycling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by spir
 * Date2019/4/10 Time 17:50
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    NewsMapper newsMapper;

    @Autowired
    UserMapper userMapper;


    @Override
    public boolean addUser(User user) {
        return 1 == userMapper.addUser(user);
    }

    @Override
    public List<News> queryNews() {

        return newsMapper.queryNews();
    }

    @Override
    public int registerUser(String username, String password) {

        return userMapper.registerUser(username,password);
    }

    @Override
    public User verifyUser(User user) {

        return userMapper.verifyUser( user);
    }
}
