package com.spir.ggcycling.service;

import com.spir.ggcycling.bean.News;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * created by spir
 * Date2019/4/11 Time 20:14
 */

public interface NewsService {
    String saveImage(MultipartFile file) throws IOException;

    boolean addNews(News news);

    String like(String  newsId,String userId);

    String dislike(String newsId, String userId);
}
