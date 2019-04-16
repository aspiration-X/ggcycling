package com.spir.ggcycling.controller;

import com.spir.ggcycling.bean.*;
import com.spir.ggcycling.service.CommentService;
import com.spir.ggcycling.service.MessageService;
import com.spir.ggcycling.service.NewsService;
import com.spir.ggcycling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * created by spir
 * Date2019/4/14 Time 16:37
 */
@Controller
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    NewsService newsService;
    @Autowired
    UserService userService;
    @Autowired
    MessageService messageService;
    @Value("${server.servlet.context-path}")
    String contextPath;



    /**
     * 查看某一个新闻的评论
     * @param newsId
     * @param model
     * @return
     */
    @RequestMapping("/news/{newsId}")
    public String detail(@PathVariable int newsId, Model model){
        ArrayList<CommentVo> commentVos = new ArrayList<>();
        News news = newsService.querySingleNews(newsId);
        if(news != null){
            //newsId对应数据表表中entityId，Entity.news对应表中EntityType
            List<Comment> comments = commentService.queryAllComments(newsId, Entity.news);

            for (Comment comment : comments) {
                User user = userService.queryUserByUserId(comment.getUserId());
                commentVos.add(new CommentVo(comment,user));
            }
            int like = Integer.parseInt(newsService.queryLikeCount(String.valueOf(newsId), String.valueOf(news.getUserId())));
            model.addAttribute("like",like);
            model.addAttribute("news",news);
            model.addAttribute("owner",news.getUser());
            model.addAttribute("commentVos",commentVos);
            model.addAttribute("contextPath",contextPath);
        }
        return "detail";
    }

    /**
     * 提交评论
     * @param newsId
     * @param content
     * @return
     */
    @RequestMapping("addComment")
    public String addComment(int newsId, String content, HttpSession session){
        User user = (User) session.getAttribute("user");
        commentService.addCommentForNewsId(newsId,content,Entity.news,user.getId());

        User toUser= newsService.queryUserByNewsId(newsId);
        String systemContent = user.getName() + "给" + toUser.getName() +"评论了。";
        messageService.addMessage(0,toUser.getId(),systemContent);//0为系统
        return "redirect:/news/" + String.valueOf(newsId);
    }




}
