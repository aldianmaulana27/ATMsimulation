package com.atm.simulation.service.impl;

import com.atm.simulation.entity.Balance;
import com.atm.simulation.repository.BalanceRepository;
import com.atm.simulation.repository.impl.BalanceRepositoryImpl;
import com.atm.simulation.service.BalanceService;

public class BalanceServiceImpl implements BalanceService {
    private BalanceRepositoryImpl balanceRepository;

    public BalanceServiceImpl(BalanceRepository balanceRepository) {
        this.balanceRepository = (BalanceRepositoryImpl) balanceRepository;
    }

    @Override
    public Balance getBalance(Integer accNumb) {
        return balanceRepository.getBalance(accNumb);
    }

    @Override
    public void addBalance(Balance balance) {
        balanceRepository.add(balance);
    }

    @Override
    public void updateBalance(Integer accNumb, Integer balance) {
        balanceRepository.updateBalance(accNumb,balance);
    }
}
