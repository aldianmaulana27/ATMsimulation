package com.atm.simulation.service;

import com.atm.simulation.entity.TransactionHistory;

import java.util.List;

public interface TransactionService {
    Integer withdraw(Integer accNo, Integer amount);
    void fundTransaction(Integer accNo, String accountDest, String amount, Integer random);
    List<TransactionHistory> getAllListByAccNo(Integer accNo);
}
