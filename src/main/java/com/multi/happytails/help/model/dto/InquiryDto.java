package com.multi.happytails.help.model.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InquiryDto {
    private long inquiryNo;
    private String helpCategoryCode;
    private String writerId;
    private String title;
    private String content;
    private char result; // N대기 S열람 Y완료
    private Date createDate;
}
