package com.atm.simulation.repository;

import com.atm.simulation.entity.Account;


import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    void addAccount(Account account);
    void addAccount();
    void addAccount(List<Account> accounts);
    List<Account> getAll();

    Optional<Account> getAccount(Integer name);
    Optional<Account> getAccount(Integer accNo, String pin);

}
