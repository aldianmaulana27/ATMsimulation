package com.atm.simulation.service;

import com.atm.simulation.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<Account> showAllAccount();
    Optional<Account> login(Integer accNo, String pin);
    Account getAccount(Integer name);
    void addAccount(Account account);




}
