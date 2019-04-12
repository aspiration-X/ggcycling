package com.spir.ggcycling.controller;

import com.spir.ggcycling.bean.News;
import com.spir.ggcycling.bean.User;
import com.spir.ggcycling.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

/**
 * created by spir
 * Date2019/4/11 Time 20:14
 */
@Controller
public class NewsController {

    @Autowired
    NewsService newsService;

    @RequestMapping("/uploadImage")
    @ResponseBody
    public HashMap uploadImage(MultipartFile file){
        HashMap map = new HashMap();
        //String originalFilename = file.getOriginalFilename();
        //File file1 = new File(originalFilename);
        try {
            String url = newsService.saveImage(file);
            map.put("code",0);
            map.put("msg",url);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("code",1);
            map.put("msg","error");
        }
        return map;
    }
    @RequestMapping("user/addNews")
    @ResponseBody
    public HashMap addNews(News news, HttpSession session){
        HashMap map = new HashMap();
        User user = (User) session.getAttribute("user");
        news.setUserId(user.getId());
        boolean b = newsService.addNews(news);
        if (b){
            map.put("code",0);
        }else {
            map.put("code",1);
            map.put("msg","error");
        }
        return map;
    }

   /* @RequestMapping("/news/{newsId}")
    public String*/

    @RequestMapping("/like")
    @ResponseBody
    public HashMap like(int newsId,HttpSession session){
        HashMap map = new HashMap();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        String msg = newsService.like(String.valueOf(newsId),String.valueOf(userId));
        map.put("code",0);
        map.put("msg",msg);
        return map;
    }

    @RequestMapping("/dislike")
    @ResponseBody
    public HashMap dislike(int newsId,HttpSession session){
        HashMap map = new HashMap();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        String msg = newsService.dislike(String.valueOf(newsId),String.valueOf(userId));
        map.put("code",0);
        map.put("msg",msg);
        return map;
    }



}
