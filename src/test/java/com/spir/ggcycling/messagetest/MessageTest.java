package com.spir.ggcycling.messagetest;

import com.spir.ggcycling.bean.Message;
import com.spir.ggcycling.dao.MessageMapper;
import com.spir.ggcycling.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * created by spir
 * Date2019/4/17 Time 0:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageTest {
    @Autowired
    MessageService messageServicel;
    @Autowired
    MessageMapper messageMapper;

    @Test
    public void queryAllMessqgesByToId(){
        List<Message> messages = messageMapper.queryAllMessqgesByToId(140);
        System.out.println(messages);
    }
}
