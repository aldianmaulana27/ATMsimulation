package com.atm.simulation.repository.impl;

import com.atm.simulation.entity.Account;
import com.atm.simulation.entity.Balance;
import com.atm.simulation.entity.DetailAccount;
import com.atm.simulation.repository.AccountRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AccountRepositoryImpl implements AccountRepository {

    public List<Account> listAccount = new ArrayList<>();
    private final DetailAccountRepositoryImpl detailAccountRepository = new DetailAccountRepositoryImpl();
    private final BalanceRepositoryImpl balanceRepository = new BalanceRepositoryImpl();

    @Override
    public void addAccount(Account account) {
        listAccount.add(account);
    }

    @Override
    public void addAccount() {
        addAccount(new Account(112233,"012108", new DetailAccount("John Doe",112233), new Balance(100,112233)));
        addAccount(new Account(112244,"932012", new DetailAccount("Jane Doe",112244), new Balance(30,112244)));
    }

    @Override
    public List<Account> getAll() {
        return listAccount;
    }

    @Override
    public Optional<Account> getAccount(Integer accNumb) {
        return listAccount.stream().filter(acc -> acc.getAccountNumber().equals(accNumb))
                .findFirst();
    }

    @Override
    public Optional<Account> getAccount(Integer accNo, String pin) {
        return listAccount.stream()
                .filter(acc -> acc.getAccountNumber().equals(accNo) && acc.getPin().equalsIgnoreCase(pin)).findFirst();


    }

    @Override
    public void remove(String name) {

    }

    @Override
    public void updateAccountBalance(Integer accNumb,Integer balance) {
        List<Account> accounts = getAll();
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accNumb)) {
                account.getBalance().setBalance(balance);
            }
        }
    }

}
