package com.multi.happytails.community.reply.model.dao;

import com.multi.happytails.community.reply.model.dto.ReplyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyDAO {
    void insertReply(ReplyDTO replyDTO);
    ReplyDTO selectReplyById(int replyDTO);
    void updateReply(ReplyDTO replyDTO);
    void deleteReply(int replyDTO);

}
