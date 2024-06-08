package com.atm.simulation.service;

import com.atm.simulation.entity.Balance;

public interface BalanceService {

    Balance getBalance(Integer name);
    void addBalance(Balance balance);
    void updateBalance(Integer accNumb, Integer balance);

}
