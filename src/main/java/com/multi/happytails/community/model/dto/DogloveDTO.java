package com.multi.happytails.community.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DogloveDTO {
    private Long dogloveNo;
    private String userId;
    private String title;
    private String content;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer recommendCount;
    private String categoryCode;

}
