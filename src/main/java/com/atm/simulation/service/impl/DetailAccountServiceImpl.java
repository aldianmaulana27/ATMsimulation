package com.atm.simulation.service.impl;

import com.atm.simulation.entity.DetailAccount;
import com.atm.simulation.repository.DetailAccountRepository;
import com.atm.simulation.repository.impl.DetailAccountRepositoryImpl;
import com.atm.simulation.service.DetailAccountService;

import java.util.List;

public class DetailAccountServiceImpl implements DetailAccountService {

    private DetailAccountRepositoryImpl detailAccountRepository;
    @Override
    public List<DetailAccount> showAllUser() {
        return detailAccountRepository.getAll();
    }

    @Override
    public DetailAccount showUser(Integer accNumb) {
        return detailAccountRepository.getDetailAccount(accNumb);
    }

    @Override
    public void addUser(DetailAccount detailAccount) {
        detailAccountRepository.add(detailAccount);
    }
}
