package com.atm.simulation.service.impl;

import com.atm.simulation.entity.Account;
import com.atm.simulation.repository.AccountRepository;
import com.atm.simulation.service.AccountService;
import com.atm.simulation.util.InputUtil;
import com.atm.simulation.util.ValidationUtil;

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
    public Optional<Account> login(String accNo, String pin) {
        boolean check = ValidationUtil.checkLength(accNo, pin);
        boolean check2 = ValidationUtil.isNumber(accNo, pin);

        if (check2 && check){
            return accountRepository.getAccount(InputUtil.integerConvert(accNo),pin);
        }
        return Optional.empty();
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

    @Override
    public void updateAccountBalance(Integer accNo, Integer balance) {
                accountRepository.updateAccountBalance(accNo,balance);
    }
}
