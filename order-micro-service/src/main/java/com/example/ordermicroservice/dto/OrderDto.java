package com.example.ordermicroservice.dto;

import com.example.ordermicroservice.domain.OrderEntity;
import com.example.ordermicroservice.vo.ResponseOrder;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    private String productId;

    private String userId;

    private Integer qty;

    private Integer unitPrice;

    public OrderEntity toEntity() {
        return OrderEntity.builder()
                .productId(productId)
                .userId(UUID.fromString(userId))
                .qty(qty)
                .unitPrice(unitPrice)
                .totalPrice(qty * unitPrice)
                .build();
    }

    public ResponseOrder toResponse(OrderEntity orderEntity){
        return ResponseOrder.builder()
                .orderId(String.valueOf(orderEntity.getOrderId()))
                .productId(orderEntity.getProductId())
                .qty(orderEntity.getQty())
                .unitPrice(orderEntity.getUnitPrice())
                .totalPrice(orderEntity.getTotalPrice())
                .createAt(orderEntity.getCreatedAt())
                .build();
    }
}
