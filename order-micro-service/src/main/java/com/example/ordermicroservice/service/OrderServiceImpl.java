package com.example.ordermicroservice.service;

import com.example.ordermicroservice.domain.OrderEntity;
import com.example.ordermicroservice.dto.OrderDto;
import com.example.ordermicroservice.repository.OrderRepository;
import com.example.ordermicroservice.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    @Override
    public ResponseOrder createOrder(OrderDto orderDto) {
        OrderEntity orderEntity = orderDto.toEntity();
        OrderEntity order = orderRepository.save(orderEntity);
        return orderDto.toResponse(order);
    }

    @Override
    public ResponseOrder getOrderByOrderId(String orderId) {
        return new OrderDto().toResponse(orderRepository.findByOrderId(UUID.fromString(orderId)));
    }

    @Override
    public Iterable<OrderEntity> getOrderByUserId(String userId) {
        return orderRepository.findByUserId(UUID.fromString(userId));
    }
}
