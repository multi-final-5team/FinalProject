package com.multi.happytails.community.reply.service;

import com.multi.happytails.community.reply.model.dao.ReplyDAO;
import com.multi.happytails.community.reply.model.dto.ReplyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReplyService {

    @Autowired
    private static ReplyDAO replyDAO;

    /**
     * 댓글 추가
     * @param replyDTO 댓글 데이터
     */
    public static void addReply(ReplyDTO replyDTO) {
        LocalDateTime now = LocalDateTime.now();
        replyDTO.setCreatedDate(now);
        replyDTO.setUpdatedDate(now);
        replyDAO.insertReply(replyDTO);
    }

    /**
     * 댓글을 ID로 조회
     * @param replyNo 댓글 ID
     * @return 댓글 데이터
     */
    public static ReplyDTO getReplyById(int replyNo) {
        return replyDAO.selectReplyById(replyNo);
    }

    /**
     * 댓글 업데이트.
     * @param replyDTO 댓글 데이터
     */
    public static void updateReply(ReplyDTO replyDTO) {
        replyDTO.setUpdatedDate(LocalDateTime.now());
        replyDAO.updateReply(replyDTO);
    }

    /**
     * 댓글 ID로 삭제
     * @param replyNo 댓글 ID
     */
    public static void deleteReply(int replyNo) {
        replyDAO.deleteReply(replyNo);
    }
}
