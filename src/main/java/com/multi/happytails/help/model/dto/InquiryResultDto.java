package com.multi.happytails.help.model.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
public class InquiryResultDto {
    private long inquiryResultNo;
    private long inquiryNo;
    private String content;
    private Date createDate;
}
