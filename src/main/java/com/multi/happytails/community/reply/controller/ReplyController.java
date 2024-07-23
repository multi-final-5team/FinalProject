package com.multi.happytails.community.reply.controller;

import com.multi.happytails.community.reply.model.dto.ReplyDTO;
import com.multi.happytails.community.reply.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/community/doglove/{dogloveNo}/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    private final AtomicInteger anonymousCounter = new AtomicInteger(1);

    @PostMapping(consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<String> addReply(
            @PathVariable("dogloveNo") int dogloveNo,
            @RequestParam String content) {

        // 익명 이름 생성
        String writerid = "익명" + anonymousCounter.getAndIncrement();

        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setForeignNo(dogloveNo);
        replyDTO.setContent(content);
        replyDTO.setWriterId(writerid);
        replyService.addReply(replyDTO);
        return ResponseEntity.ok("댓글이 성공적으로 추가되었습니다.");
    }
}