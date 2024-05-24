package com.atm.simulation.repository.impl;

import com.atm.simulation.entity.Account;
import com.atm.simulation.entity.User;
import com.atm.simulation.repository.AccountRepository;

import java.util.Objects;

public class AccountRepositoryImpl implements AccountRepository {
    UserRepositoryImpl userRepository;

    @Override
    public Account getAccount(String userName) {
        for (User user : userRepository.listUser) {
            if(userName.equalsIgnoreCase(user.getName())){
                return user.getAccount();
            }
        }
        return null;
    }
    @Override
    public Account getAccountByAccNumb(Integer accountNumber){
        for (User user : userRepository.listUser) {
            if(Objects.equals(accountNumber, user.getAccount().getAccountNumber())){
                return user.getAccount();
            }
        }
        return null;
    }

    @Override
    public void add(Account account) {

    }
}
