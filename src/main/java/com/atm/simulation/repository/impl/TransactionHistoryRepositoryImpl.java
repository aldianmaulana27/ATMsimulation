package com.atm.simulation.repository.impl;

import com.atm.simulation.entity.TransactionHistory;
import com.atm.simulation.repository.TransactionHistoryRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionHistoryRepositoryImpl implements TransactionHistoryRepository {
    List<TransactionHistory> listTransactionHistory = new ArrayList<>();

    @Override
    public void addTransactionHistory(TransactionHistory transactionHistory) {
        listTransactionHistory.add(transactionHistory);
    }

    @Override
    public List<TransactionHistory> getAllTransactionHistory(Integer accNo, int maxSize) {
        return listTransactionHistory.stream().sorted(Comparator.comparing(TransactionHistory::getDate).reversed())
                .filter(p -> p.getAccountNumber().equals(accNo)).limit(maxSize).collect(Collectors.toList());
    }
}
