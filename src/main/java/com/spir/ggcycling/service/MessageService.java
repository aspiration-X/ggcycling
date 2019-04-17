package com.spir.ggcycling.service;

import com.spir.ggcycling.bean.Message;

import java.util.List;

/**
 * created by spir
 * Date2019/4/15 Time 20:29
 */
public interface MessageService {
    /**
     * 通过登录用户的id（此时对应数据表中的字段是toId）
     * @param id user的id
     * @return 与该用户相关的所有信息，包括该用户发送或接收的信息
     */
    List<Message> queryAllMessqgesByUserId(Integer id);

    /**
     * ID为fromId的用户向ID为toId的用户发送了消息（content）
     * @param fromId 信息发送者
     * @param toId  信息接收者
     * @param content   发送内容
     * @return
     */
    boolean addMessage(Integer fromId, int toId, String content);

    /**
     * 通过conversationId会话id查找一组对应的message
     * @param conversationId
     * @return 这些message的list集合
     */
    List<Message> queryOneGroupMessageByConversation(String conversationId);

    void removeOneMessage(int id);

    void removeConversationByConversationId(String conversationId);
}
