package com.spir.ggcycling.service;

import com.spir.ggcycling.bean.News;
import com.spir.ggcycling.bean.User;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


/**
 * created by spir
 * Date2019/4/11 Time 20:14
 */

public interface NewsService {
    String saveImage(MultipartFile file) throws IOException;

    boolean addNews(News news);

    /**
     *
     * @param newsId
     * @param userId
     * @return
     */
    String like(String  newsId,String userId);

    String dislike(String newsId, String userId);

    News querySingleNews(int newsId);


    String queryLikeCount(String valueOf, String valueOf1);

    /**
     * 通过新闻的Id找出该新闻分享用户的ID
     * @param newsId 新闻的Id
     * @return 分享该新闻的用户
     */
    User queryUserByNewsId(int newsId);
}
