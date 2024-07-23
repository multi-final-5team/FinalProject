package com.multi.happytails.shop.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class DeliveryStatusDTO {

    private int no;
    private int purchaseNo;
    private String id;
    private String statusCode;
    private int invoiceNumber;
}
