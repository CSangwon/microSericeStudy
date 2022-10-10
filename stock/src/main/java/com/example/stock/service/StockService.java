package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StockService {

    private StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

//    @Transactional
    public synchronized void decreaseStock(Long id, Integer qty) {
        Stock stock = stockRepository.findById(id).orElseThrow();
        stock.decrease(qty);
        stockRepository.save(stock);
    }
}
