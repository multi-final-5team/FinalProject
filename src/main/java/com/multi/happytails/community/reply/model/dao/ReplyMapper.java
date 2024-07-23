package com.multi.happytails.community.reply.model.dao;

import com.multi.happytails.community.reply.model.dto.ReplyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {



    void insertReply(ReplyDTO replyDTO);

    List<ReplyDTO> selectReplyByColumnNo(@Param("foreignNo") Integer foreignNo);

    void deleteReply(@Param("communityReplyNo") int communityReplyNo);
}
