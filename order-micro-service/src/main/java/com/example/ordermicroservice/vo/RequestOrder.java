package com.example.ordermicroservice.vo;

import com.example.ordermicroservice.dto.OrderDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestOrder {

    private String productId;

    private Integer qty;

    private Integer unitPrice;

    public OrderDto toDto(String userId) {
        return OrderDto.builder()
                .productId(productId)
                .userId(userId)
                .qty(qty)
                .unitPrice(unitPrice)
                .build();
    }
}
