package com.spir.ggcycling.controller;

import com.spir.ggcycling.bean.News;
import com.spir.ggcycling.bean.Vo;
import com.spir.ggcycling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * created by spir
 * Date2019/4/16 Time 22:50
 */
@Controller
public class HomeController {
    @Value("${server.servlet.context-path}")
    String contextPath;

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String dis(Model model){
        return queryNews(model);
    }

    /**
     * 显示首页
     * @param model
     * @return
     */
    @RequestMapping("home")
    public String queryNews(Model model){
        List<News> newsList =  userService.queryNews();
        List<Vo> vos = new ArrayList<>();
        for (News news : newsList) {
            vos.add(new Vo(news,news.getUser(),news.getLikeCount()));
        }
        model.addAttribute("vos",vos);
        model.addAttribute("contextPath",contextPath);
        return "home";
    }

    /**
     * 登录并评论
     * @param model
     * @param pop 前台给的参数
     * @return
     */
    @RequestMapping(value = "home",params= {"pop"})
    public String toLogin(Model model, int pop, HttpSession session){
        if(session.getAttribute("user")!=null){
            model.addAttribute("pop",pop);
        }else
            model.addAttribute("pop",0);
        return queryNews(model);
    }



}
