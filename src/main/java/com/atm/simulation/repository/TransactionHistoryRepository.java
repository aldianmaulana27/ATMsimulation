package com.atm.simulation.repository;

import com.atm.simulation.entity.TransactionHistory;

import java.util.List;
import java.util.Optional;

public interface TransactionHistoryRepository {
    void addTransaction(TransactionHistory transactionHistory);
    Optional<TransactionHistory> gerTransactionByAcoNo(Integer accNo);
    List<TransactionHistory> getTransactionsByAccNo(Integer accNo);
}
