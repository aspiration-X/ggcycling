package com.spir.ggcycling.service;

import com.spir.ggcycling.bean.News;
import com.spir.ggcycling.bean.User;

import java.util.List;

/**
 * created by spir
 * Date2019/4/10 Time 17:48
 */

public interface UserService {

    /**
     * 用户注册
     * @param user
     * @return
     */
    boolean addUser(User user);

    /**
     * 首页新闻显示
     * @return
     */
    List<News> queryNews();

    int registerUser(String username, String password);

    User verifyUser(User user);
}
