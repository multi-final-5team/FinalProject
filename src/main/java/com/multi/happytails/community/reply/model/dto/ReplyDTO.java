package com.multi.happytails.community.reply.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReplyDTO {

    private int communityReplyNo;
    private String communityCategoryCode;
    private int foreignNo;
    private String writerId;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

}
