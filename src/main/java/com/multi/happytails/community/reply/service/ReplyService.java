package com.multi.happytails.community.reply.service;

import com.multi.happytails.community.reply.model.dao.ReplyMapper;
import com.multi.happytails.community.reply.model.dto.ReplyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {

    @Autowired
    private ReplyMapper replyMapper;


    /**
     * 댓글 추가
     * @param replyDTO 댓글 정보
     */
    public void addReply(ReplyDTO replyDTO) {
        if (replyDTO.getCommunityCategoryCode() == null || replyDTO.getCommunityCategoryCode().isEmpty()) {
            replyDTO.setCommunityCategoryCode("L");
            replyMapper.insertReply(replyDTO);

        }
    }

    /**
     * 댓글 목록 조회
     @param columnNo 게시글 번호
     @return 댓글 목록
     */
    public List<ReplyDTO> getRepliesByColumnNo(int columnNo) {
        return replyMapper.selectReplyByColumnNo(columnNo);
    }
}
