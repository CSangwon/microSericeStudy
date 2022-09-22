package com.example.usermicroservice.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ResponseOrder {

    private String productId;

    private Integer qty;

    private Integer unitPrice;

    private Integer totalPrice;

    private Date createdAt;

    private String orderId;
}
