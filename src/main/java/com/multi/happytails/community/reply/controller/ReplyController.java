package com.multi.happytails.community.reply.controller;

import com.multi.happytails.community.reply.model.dto.ReplyDTO;
import com.multi.happytails.community.reply.service.ReplyService;
import com.multi.happytails.upload.model.dto.UploadDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doglove/{dogloveNo}")
public class ReplyController {

    private static final String REPLY_CODE = "L";


    @Autowired
    private ReplyService replyService;

    @PostMapping("/reply/add")
    public String addReply(@PathVariable int RERLY_CODE, @RequestParam String categoryCode, @RequestBody ReplyDTO replyDTO) {
        replyDTO.setCommunityReplyNo(RERLY_CODE);
        replyService.addReply(replyDTO);
        return "댓글이 추가되었습니다.";
    }

    @GetMapping("/reply/{replyNo}")
    public ReplyDTO getReply(@PathVariable int replyNo) {
        return replyService.getReplyById(replyNo);
    }

    @PutMapping("/reply/update")
    public String updateReply(@PathVariable int RERLY_CODE, @RequestBody ReplyDTO replyDTO) {
       // ReplyDTO replyDTO = new ReplyDTO();

        replyDTO.setCommunityReplyNo(RERLY_CODE);
        replyService.updateReply(replyDTO);
        return "댓글이 수정되었습니다.";
    }

    @DeleteMapping("/reply/delete/{replyNo}")
    public String deleteReply(@PathVariable int replyNo) {
        replyService.deleteReply(replyNo);
        return "댓글이 삭제되었습니다.";
    }
}
