package com.atm.simulation.repository;

import com.atm.simulation.entity.Account;


import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    void addAccount(Account account);

    void addAccount();

    List<Account> getAll();

    Optional<Account> getAccount(Integer name);
    Optional<Account> getAccount(Integer accNo, String pin);

    void remove(String name);

    void updateAccountBalance(Integer accNumb, Integer balance);
}
