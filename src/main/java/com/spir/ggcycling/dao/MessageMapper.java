package com.spir.ggcycling.dao;

import com.spir.ggcycling.bean.Message;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKeyWithBLOBs(Message record);

    int updateByPrimaryKey(Message record);

    /**
     * 通过user的id查找与该用户相关的所有message
     * @param id
     * @return 所有message的一个list集合
     */
    List<Message> queryAllMessqgesByToId(@Param("userId") Integer id);

    int addMessage(@Param("fromId") Integer fromId, @Param("toId") int toId,
                   @Param("content") String content, @Param("createdDate") Date date,
                   @Param("conversationId") String conversationId);

    /**
     * 通过conversationId会话id查找一组对应的message
     * @param conversationId
     * @return 这些message的list集合
     */
    List<Message> queryOneGroupMessageByConversation(String conversationId);

    @Delete("delete from message where conversation_id = #{conversationId}")
    void deleteByConversationId(String conversationId);
}