package com.spir.ggcycling.controller;

import com.spir.ggcycling.bean.Message;
import com.spir.ggcycling.bean.User;
import com.spir.ggcycling.service.MessageService;
import com.spir.ggcycling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * created by spir
 * Date2019/4/15 Time 19:55
 */
@Controller
public class MessageController {

    @Autowired
    MessageService messageService;
    @Value("${server.servlet.context-path}")
    String contextPath;
    @Autowired
    UserService userService;

    /**
     * 查看-站内信
     * @param model
     * @param session 存放登录用户的信息
     * @return
     */
    @RequestMapping("msg/list")
    public String messageList(Model model, HttpSession session){
        List<Message> msgs = null;
        User user = (User) session.getAttribute("user");
        if(user != null){
             msgs = messageService.queryAllMessqgesByUserId(user.getId());//session里user的id就是toId（接收方的id）
        }
        model.addAttribute("conversations",msgs);
        model.addAttribute("contextPath",contextPath);
    return "letter";
    }

    /**
     * 显示会话详情
     * @param conversationId
     * @param model
     * @return
     */
    @RequestMapping(value = "msg/detail" )
    public String messageDetail(String conversationId , Model model){
        List<Message> msgs = messageService.queryOneGroupMessageByConversation(conversationId);
        model.addAttribute("messages",msgs);
        model.addAttribute("contextPath",contextPath);
        return "letterDetail";
    }

    /**
     * 删除单个站内信信息
     * @param id 该message主键id
     * @param conversationId
     * @param model
     * @return
     */
    @RequestMapping("msg/delLink")
    public String messageDelLink( int id,String conversationId,Model model){
        messageService.removeOneMessage(id);
        return messageDetail(conversationId,model);
    }

    @RequestMapping("msg/delConversation")
    public String messageDelConversation(String conversationId,Model model,HttpSession session){
        messageService.removeConversationByConversationId(conversationId);
        return messageList(model,session);
    }


    /**
     * 点击发送私信，转到该页面
     * @param model
     * @return
     */
    @RequestMapping("user/tosendmsg")
    public String toSendMessage(Model model){
        model.addAttribute("contextPath",contextPath);
        return "sendmsg";
    }

    /**
     * 提交私信
     * @param session
     * @param model
     * @param toName
     * @param content
     * @return
     */
    @RequestMapping("user/msg/addMessage")
    @ResponseBody
    public HashMap addMessage(HttpSession session, Model model,
                              @RequestParam("toName") String toName,
                              @RequestParam("content") String content){
        HashMap map = new HashMap();
        User user = (User) session.getAttribute("user");
        Integer fromId = user.getId();
        User toUser = userService.queryUserByName(toName );
        int toId = toUser.getId();
        boolean b = messageService.addMessage(fromId,toId,content);
        if (b){
            map.put("code",0);
        }else {
            map.put("code",1);
            map.put("msg","error");
        }
        return map;
    }

}
