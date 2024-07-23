package com.multi.happytails.shop.model.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class ReviewDTO {

    private int no;
    private String id;
    private int goodsNo;
    private int starRating;
    private String content;
    private Timestamp reviewDate;
}
