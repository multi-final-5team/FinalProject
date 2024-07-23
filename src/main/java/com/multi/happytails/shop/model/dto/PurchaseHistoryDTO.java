package com.multi.happytails.shop.model.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class PurchaseHistoryDTO {

    private int no;
    private String id;
    private String address;
    private String requirements;
    private int goodsNo;
    private int quantity;
    private Timestamp purchaseDate;
}
