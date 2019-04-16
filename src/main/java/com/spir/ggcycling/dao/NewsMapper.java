package com.spir.ggcycling.dao;


import com.spir.ggcycling.bean.News;
import com.spir.ggcycling.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

    List<News> queryNews();

    News querySingleNews(int newsId);

    /**
     * 通过新闻的Id找出该新闻分享用户的ID
     * @param newsId 新闻的Id
     * @return 分享该新闻的用户
     */
    User queryUserByNewsId(@Param("newsId") int newsId);
}