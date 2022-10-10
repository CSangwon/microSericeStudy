package com.example.usermicroservice.client;

import com.example.usermicroservice.vo.ResponseOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="order-micro-service")
public interface OrderServiceClient {


    @GetMapping("/order-micro-service/{userId}/orders")
    List<ResponseOrder> getOrders(@PathVariable String userId);

}
