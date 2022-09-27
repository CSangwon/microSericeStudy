package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import org.aspectj.lang.annotation.After;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StockServiceTest {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockService stockService;

    @BeforeEach
    public void beforeEach(){
        Stock stock = Stock.builder().productId(1L).qty(100).build();
        stockRepository.save(stock);
    }

    @AfterEach
    public void afterEach(){
        stockRepository.deleteAll();
    }

    @Test
    public void stock_decrease() throws Exception{
        //given
        stockService.decreaseStock(1L, 1);
        //when
        Stock stock = stockRepository.findById(1L).orElseThrow();
        //then
        assertThat(stock.getQty()).isEqualTo(99);
    }

    @Test
    public void stock_decrease_100() throws Exception {
        int threadCount = 100;

        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i <= threadCount; i++) {
            executorService.submit(() -> {
                try {
                    stockService.decreaseStock(1L, 1);
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.countDown();

        Stock stock = stockRepository.findById(1L).orElseThrow();
        //then

        assertThat(stock.getQty()).isEqualTo(0);


    }

}
