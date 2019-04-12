package com.spir.ggcycling.controller;


import com.spir.ggcycling.bean.News;
import com.spir.ggcycling.bean.oResult;
import com.spir.ggcycling.bean.User;
import com.spir.ggcycling.bean.Vo;
import com.spir.ggcycling.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


/**
 * created by spir
 * Date2019/4/10 Time 16:36
 */
@Controller
//@SessionAttributes("user")
public class UserController {
    @Value("${server.servlet.context-path}")
    String contextPath;

    @Autowired
    UserService userService;

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


    @RequestMapping("/register")
    @ResponseBody
    @Transactional
    public oResult register(String username, String password, Model model, HttpSession session){
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        boolean b = userService.addUser(user);
        if(b){
            model.addAttribute("user",user);
            return successfulStuatus();
        }
        return failStatus();
    }

    @RequestMapping("/login")
    @ResponseBody
    public oResult login (String username, String password, Model model, HttpSession session){
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        User user1 = userService.verifyUser(user);
        if(user1 != null){
            session.setAttribute("user",user1);
            //model.addAttribute("user",user);
            return successfulStuatus();
        }
        return failStatus();
    }

    @RequestMapping("logout")
    public ModelAndView logout(HttpSession session){
        if(session != null & session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }
        return new ModelAndView("redirect:/home");

    }

    public oResult successfulStuatus(){
        return new oResult(0,"注册成功");
    }
    public oResult failStatus(){
        return new oResult(1,"注册失败");
    }

}
