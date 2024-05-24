package com.atm.simulation.service;

import com.atm.simulation.entity.Account;

public interface AccountService {

    Account getAccount(String name);
    void addAccount(Account account);


}
