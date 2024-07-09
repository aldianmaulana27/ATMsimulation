package com.atm.simulation.repository.impl;

import com.atm.simulation.entity.Account;
import com.atm.simulation.repository.AccountRepository;

import java.util.*;

public class AccountRepositoryImpl implements AccountRepository {

    public List<Account> listAccount = new ArrayList<>();

    @Override
    public void addAccount(Account account) {
        listAccount.add(account);
    }

    @Override
    public void addAccount() {
        addAccount(new Account(112233,"012108","John Deep", 100));
        addAccount(new Account(112244,"932012","Johe Deep", 30));
    }

    @Override
    public void addAccount(List<Account> accounts) {
//        Set<Account> list1 = new HashSet<>(listAccount);
//        Set<Account> list2 = new HashSet<>(accounts);
//        list2.removeAll(list1);
        listAccount.addAll(accounts);
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
                .filter(acc -> acc.getAccountNumber().equals(accNo) && acc.checkPin(pin)).findFirst();

    }


}
