package com.multi.happytails.shop.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class CartDTO {

    private int no;
    private String id;
    private int goodsNo;
    private int purchaseQuantity;
}
