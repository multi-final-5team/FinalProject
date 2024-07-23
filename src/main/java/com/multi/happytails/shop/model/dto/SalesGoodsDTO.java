package com.multi.happytails.shop.model.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class SalesGoodsDTO {

    private int no;
    private String id;
    private String address;
    private String name;
    private int price;
    private int quantity;
    private String content;
    private String categoryCode;
    private Timestamp createDate;
    private Timestamp updateDate;

}

