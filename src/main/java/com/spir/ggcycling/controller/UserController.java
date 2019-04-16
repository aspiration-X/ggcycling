package com.spir.ggcycling.controller;


import com.spir.ggcycling.bean.News;
import com.spir.ggcycling.bean.oResult;
import com.spir.ggcycling.bean.User;
import com.spir.ggcycling.bean.Vo;
import com.spir.ggcycling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    /**
     * 注册
     * @param username
     * @param password
     * @param model
     * @param session
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    @Transactional
    public oResult register(String username, String password, Model model, HttpSession session){
        User user = new User();
        user.setName(username);
        user.setPassword(password);
        String headUrl = "https://ggcycling.oss-cn-hangzhou.aliyuncs.com/headimage/"+ ((int)(Math.random()*5)+1) +".jpeg";
        user.setHeadUrl(headUrl);
        boolean b = userService.addUser(user);
        if(b){
            model.addAttribute("user",user);
            return successfulStuatus();
        }
        return failStatus();
    }

    /**
     * 登录
     * @param username
     * @param password
     * @param model
     * @param session
     * @return
     */
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

    /**
     * 注销
     * @param session
     * @return
     */
    @RequestMapping("logout")
    public ModelAndView logout(HttpSession session){
        if(session != null & session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }
        return new ModelAndView("redirect:/home");
    }

    /**
     * 显示个人详情
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping("user/{userId}")
    public String userInfo(@PathVariable int userId ,Model model){
        User user = userService.queryUserByUserId(userId);
        model.addAttribute("contextPath",contextPath);
        model.addAttribute("user",user);
        return "personal";
    }



    public oResult successfulStuatus(){
        return new oResult(0,"注册成功");
    }
    public oResult failStatus(){
        return new oResult(1,"注册失败");
    }

}
