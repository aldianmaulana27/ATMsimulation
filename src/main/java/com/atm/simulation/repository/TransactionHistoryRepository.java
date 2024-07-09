package com.atm.simulation.repository;

import com.atm.simulation.entity.TransactionHistory;

import java.util.List;

public interface TransactionHistoryRepository {
    void addTransactionHistory(TransactionHistory transactionHistory);
    List<TransactionHistory> getAllTransactionHistory(Integer accNo, int maxSize);
}
