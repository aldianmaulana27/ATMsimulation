package com.atm.simulation.repository;

import com.atm.simulation.entity.Account;
import com.atm.simulation.entity.User;

public interface AccountRepository {

    Account getAccount(String userName);
    Account getAccountByAccNumb(Integer accountNumb);
    void add(Account account);


}
