package com.atm.simulation.service;

import com.atm.simulation.entity.Balance;

public interface BalanceService {

    Balance getBalance(String name);
    void addBalance(Balance balance);

}
