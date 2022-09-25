package com.example.ordermicroservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseOrder {

    private String orderId;

    private String productId;

    private Integer qty;

    private Integer unitPrice;

    private Integer totalPrice;

    private Date createAt;

}
