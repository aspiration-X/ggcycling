package com.spir.ggcycling.controller;

import com.spir.ggcycling.bean.User;
import com.spir.ggcycling.service.MessageService;
import com.spir.ggcycling.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * created by spir
 * Date2019/4/16 Time 22:53
 */
@Controller
public class LikeController {
    @Autowired
    NewsService newsService;
    @Autowired
    MessageService messageService;
    @Value("${server.servlet.context-path}")
    String contextPath;
    /**
     * 点赞
     * 小技巧：将map.put("msg",msg);放入if中，如果用户没有登录，则用户点击不起作用
     *
     * @param newsId 被点赞的新闻的Id
     * @param session 存放Attribute"user"，为登录的用户，如果没有登录，则取出的user为null；
     * @return msg为查询所得点赞数量
     */
    @RequestMapping("/like")
    @ResponseBody
    public HashMap like(int newsId, HttpSession session){
        HashMap map = new HashMap();
        String msg = null;
        User user = (User) session.getAttribute("user");
        if(user != null){
            int userId = user.getId();
            msg = newsService.like(String.valueOf(newsId),String.valueOf(userId));
            User toUser= newsService.queryUserByNewsId(newsId);

            //如何实现这样点赞后能在已有的赞数上增加？？
            String content = "系统通知：用户" + user.getName() + "给" + toUser.getName() +"分享的文章点了一个赞。";
            messageService.addMessage(0,toUser.getId(),content);//0为系统
            map.put("msg",msg);
        }
        map.put("code",0);
        return map;
    }

    /**
     * 点踩
     * 小技巧：将map.put("msg",msg);放入if中，如果用户没有登录，则用户点击不起作用
     * @param newsId
     * @param session
     * @return
     */
    @RequestMapping("/dislike")
    @ResponseBody
    public HashMap dislike(int newsId,HttpSession session){
        HashMap map = new HashMap();
        String msg = null;
        User user = (User) session.getAttribute("user");
        if(user != null){
            int userId = user.getId();
            msg = newsService.dislike(String.valueOf(newsId),String.valueOf(userId));
            map.put("msg",msg);
        }
        map.put("code",0);
        return map;
    }
}
