package com.atm.simulation.repository.impl;

import com.atm.simulation.entity.TransactionHistory;
import com.atm.simulation.repository.TransactionHistoryRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TransactionHistoryRepositoryImpl implements TransactionHistoryRepository {
    List<TransactionHistory> listTransactionHistory = new ArrayList<>();

    @Override
    public void addTransaction(TransactionHistory transactionHistory) {
        listTransactionHistory.add(transactionHistory);
    }

    @Override
    public Optional<TransactionHistory> gerTransactionByAcoNo(Integer accNo) {
        return listTransactionHistory.stream().filter(p-> p.getAccountNumber().equals(accNo)).findFirst();
    }

    @Override
    public List<TransactionHistory> getTransactionsByAccNo(Integer accNo) {
        return listTransactionHistory.stream().sorted(Comparator.comparing(TransactionHistory::getDate).reversed()).filter(p -> p.getAccountNumber().equals(accNo)).limit(10).collect(Collectors.toList());
    }
}
