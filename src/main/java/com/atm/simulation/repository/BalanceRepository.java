package com.atm.simulation.repository;

import com.atm.simulation.entity.Account;
import com.atm.simulation.entity.Balance;

public interface BalanceRepository {

    Balance getBalance(Integer accountNumber);
    void add(Balance balance);
}
