package com.example.ordermicroservice.service;

import com.example.ordermicroservice.domain.OrderEntity;
import com.example.ordermicroservice.dto.OrderDto;
import com.example.ordermicroservice.vo.ResponseOrder;

public interface OrderService {

    ResponseOrder createOrder(OrderDto orderDto);

    ResponseOrder getOrderByOrderId(String orderId);

    Iterable<OrderEntity> getOrderByUserId(String userId);
}
