package com.atm.simulation.service;

public interface TransactionService {
    Integer withdraw(Integer accNo, Integer amount);
    void fundTransaction(Integer accNo, String accountDest, String amount, Integer random);
}
