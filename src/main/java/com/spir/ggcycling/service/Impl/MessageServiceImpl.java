package com.spir.ggcycling.service.Impl;

import com.spir.ggcycling.bean.Message;
import com.spir.ggcycling.dao.MessageMapper;
import com.spir.ggcycling.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * created by spir
 * Date2019/4/15 Time 20:29
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    @Override
    public List<Message> queryAllMessqgesByUserId(Integer id) {
        return messageMapper.queryAllMessqgesByToId(id);
    }

    @Override
    public boolean addMessage(Integer fromId, int toId, String content) {
        Date date = new Date();
        String conversationId = fromId + "_" +toId;
        return
                1 ==  messageMapper.addMessage(fromId,toId,content,date,conversationId);
    }

    @Override
    public List<Message> queryOneGroupMessageByConversation(String conversationId) {
        return messageMapper.queryOneGroupMessageByConversation(conversationId);
    }
}
