package com.atm.simulation.repository.impl;

import com.atm.simulation.entity.Account;
import com.atm.simulation.entity.Balance;
import com.atm.simulation.repository.BalanceRepository;

import java.util.ArrayList;
import java.util.List;

public class BalanceRepositoryImpl implements BalanceRepository {
   List<Balance> balanceList = new ArrayList<>();

    @Override
    public Integer getBalanceValue(Integer accountNumber) {
        for (Balance balance : balanceList) {
            if(accountNumber.equals(balance.getAccountNumber())){
                return balance.getBalance();
            }
        }
        return null;
    }

    @Override
    public Balance getBalance(Integer accountNumber) {
        for (Balance balance : balanceList) {
            if(accountNumber.equals(balance.getAccountNumber())){
                return balance;
            }
        }
        return null;
    }

    @Override
    public void add(Balance balance) {
        balanceList.add(balance);
    }

    @Override
    public void updateBalance(Integer accNumb, Integer balance) {
        for (Balance value : balanceList) {
            if (value.getAccountNumber().equals(accNumb)) {
                value.setBalance(balance);
            }
        }
    }
}