package com.multi.happytails.help.model.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
public class QuestionDto {
    private long questionNo;
    private String helpCategoryCode;
    private String questionContent;
    private String answerContent;
}
