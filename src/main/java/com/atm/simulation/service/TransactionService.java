package com.atm.simulation.service;

import com.atm.simulation.entity.Account;
import com.atm.simulation.entity.TransactionHistory;

import java.util.List;

public interface TransactionService {
    Integer withdraw(Integer accNo, Integer amount);
    void fundTransaction(Account account, String accountDest, String amount, int randomNumber);
    void addHistory(TransactionHistory transactionHistory);
    List<TransactionHistory> getAllTransactionHistory(Integer accNo);
}
