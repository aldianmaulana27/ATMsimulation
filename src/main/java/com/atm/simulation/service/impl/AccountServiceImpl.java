package com.atm.simulation.service.impl;

import com.atm.simulation.entity.Account;
import com.atm.simulation.repository.AccountRepository;
import com.atm.simulation.service.AccountService;

public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public Account getAccount(String name) {
        Account account = accountRepository.getAccountByAccNumb(Integer.parseInt(name));

        return account;
    }

    @Override
    public void addAccount(Account account) {

    }
}
