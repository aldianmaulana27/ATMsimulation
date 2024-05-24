package com.atm.simulation.repository.impl;

import com.atm.simulation.entity.Account;
import com.atm.simulation.entity.Balance;
import com.atm.simulation.entity.User;
import com.atm.simulation.repository.BalanceRepository;

public class BalanceRepositoryImpl implements BalanceRepository {
    UserRepositoryImpl userRepository;
    @Override
    public Balance getBalance(Integer accountNumber) {
        for (User user : userRepository.listUser) {
            if(accountNumber.equals(user.getAccount().getBalance().getAccountNumber())){
                return user.getAccount().getBalance();
            }
        }
        return null;
    }

    @Override
    public void add(Balance balance) {

    }
}
