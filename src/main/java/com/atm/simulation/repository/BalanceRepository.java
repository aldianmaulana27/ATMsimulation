package com.atm.simulation.repository;

import com.atm.simulation.entity.Account;
import com.atm.simulation.entity.Balance;

public interface BalanceRepository {

    Integer getBalanceValue(Integer accountNumber);
    Balance getBalance(Integer accountNumber);
    void add(Balance balance);
    void updateBalance(Integer accNumb, Integer balance);
}
