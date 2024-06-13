package com.atm.simulation.service;

import com.atm.simulation.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<Account> showAllAccount();
    Optional<Account> login(String accNo, String pin);
    Account getAccount(Integer name);
    void addAccountFromDoc(String builder);
    void addAccount(Account account);
    void addAccounts(List<Account> account);
    void updateAccountBalance(Integer accNo, Integer balance);




}
