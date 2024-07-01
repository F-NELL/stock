package com.example.stock.service.impl;


import org.springframework.beans.factory.annotation.Autowired;

import com.example.stock.pojo.LogStock;
import com.example.stock.repository.LogStockRepository;
import com.example.stock.service.LogStockService;

public class LogStockServiceImpl implements LogStockService {
    
    @Autowired
    private LogStockRepository logStockRepository;

    @Override
    public void createLogStock(LogStock logStock) {

    }
}
