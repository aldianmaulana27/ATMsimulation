package com.atm.simulation.service.impl;

import com.atm.simulation.entity.Account;
import com.atm.simulation.repository.AccountRepository;
import com.atm.simulation.service.AccountService;

import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> showAllAccount() {
        return accountRepository.getAll();
    }

    @Override
    public Optional<Account> login(Integer accNo, String pin) {
        return accountRepository.getAccount(accNo,pin);
    }

    @Override
    public Account getAccount(Integer accountNumber) {
        Optional<Account> account = accountRepository.getAccount(accountNumber);
        return account.orElse(null);
    }

    @Override
    public void addAccount(Account account) {
        accountRepository.addAccount(account);
    }
}
