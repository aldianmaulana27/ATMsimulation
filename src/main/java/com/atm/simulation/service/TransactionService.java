package com.atm.simulation.service;

public interface TransactionService {
    Integer withdraw(Integer accNo, Integer amount);
    Integer withdraw(Integer accNo, String amount);
    void fundTransaction(Integer accNo, String accountDest, String amount, Integer random);
}
