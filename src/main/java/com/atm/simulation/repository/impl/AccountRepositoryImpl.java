package com.atm.simulation.repository.impl;

import com.atm.simulation.entity.Account;
import com.atm.simulation.repository.AccountRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AccountRepositoryImpl implements AccountRepository {

    public List<Account> listAccount = new ArrayList<>();

    @Override
    public void addAccount(Account account) {
        boolean duplicate;
        Optional<Account> acc = getAccount(account.getAccountNumber());
        if(acc.isEmpty()){
             listAccount.add(account);
        }else {
            duplicate = account.equals(acc.get());
            if (duplicate){
                System.out.println("duplicate Account "+account.toString()+"\n"+
                        getAll().toString());
            }

        }

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
        for (Account account : listAccount) {
            if (account.getAccountNumber().equals(accNumb)) {
                account.getBalance().setBalance(balance);
            }
        }
    }

}
