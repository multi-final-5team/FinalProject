package com.multi.happytails.community.reply.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
public class ReplyDTO {
    private int id;
    private String communityCategoryCode; // 'S', 'C', 'O', 'L', 'R'
    private Integer foreignNo;
    private String writerId;
    private String content;
    private Timestamp createDate;

    public void setForeignNo(int foreignNo) {
        this.foreignNo = foreignNo;
    }
}
