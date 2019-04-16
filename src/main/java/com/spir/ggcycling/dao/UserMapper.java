package com.spir.ggcycling.dao;


import com.spir.ggcycling.bean.News;
import com.spir.ggcycling.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 用戶注册,插入用户名密码
     * @param user 包含username password ,且username&password均不能为null
     * @return 1:success 0:fail
     */
    int addUser(User user);

    int registerUser(@Param("name") String username, @Param("password") String password);

    User verifyUser(@Param("user") User user);

    User queryUserByName(@Param("name")String toName);
}