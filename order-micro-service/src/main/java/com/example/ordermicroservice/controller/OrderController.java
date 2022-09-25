package com.example.ordermicroservice.controller;

import com.example.ordermicroservice.domain.OrderEntity;
import com.example.ordermicroservice.dto.OrderDto;
import com.example.ordermicroservice.service.OrderService;
import com.example.ordermicroservice.vo.Greeting;
import com.example.ordermicroservice.vo.RequestOrder;
import com.example.ordermicroservice.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order-micro-service")
@RequiredArgsConstructor
public class OrderController {

    private final Greeting greeting;

    private final OrderService orderService;

    @GetMapping("/health-check")
    public String status() {
        return "It's Working in User Service on Port " + greeting.getServerPort();
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@RequestBody RequestOrder requestOrder, @PathVariable String userId) {
        return new ResponseEntity<>(orderService.createOrder(requestOrder.toDto(userId)), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ResponseOrder> getOrderByOrderID(@PathVariable String orderId) {
        return new ResponseEntity<>(orderService.getOrderByOrderId(orderId), HttpStatus.OK);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrderByUserId(@PathVariable String userId) {
        Iterable<OrderEntity> orderByUserId = orderService.getOrderByUserId(userId);

        List<ResponseOrder> result = new ArrayList<>();
        OrderDto orderDto = new OrderDto();
        orderByUserId.forEach(v ->{
            result.add(orderDto.toResponse(v));
        });
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
