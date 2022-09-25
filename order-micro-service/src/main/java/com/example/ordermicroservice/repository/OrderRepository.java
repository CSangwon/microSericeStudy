package com.example.ordermicroservice.repository;

import com.example.ordermicroservice.domain.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    OrderEntity findByOrderId(UUID orderId);

    Iterable<OrderEntity> findByUserId(UUID userId);
}
