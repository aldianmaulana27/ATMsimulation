package com.atm.simulation.service.impl;

import com.atm.simulation.entity.Account;
import com.atm.simulation.repository.AccountRepository;
import com.atm.simulation.service.AuthenticationService;
import com.atm.simulation.util.ValidationUtil;

import java.util.*;

public class AuthenticationServiceImpl implements AuthenticationService {

    private ValidationUtil validationUtil;
    private AccountRepository accountRepository;

    public AuthenticationServiceImpl(AccountRepository accountRepository, ValidationUtil validationUtil){
        this.validationUtil = validationUtil;
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> login(String accNo, String pin) {
        boolean check = validationUtil.checkLength(accNo, pin);
        boolean check2 = validationUtil.isNumber(accNo, pin);

        if (check2 && check){
            return accountRepository.getAccount(Integer.parseInt(accNo),pin);
        }

        return Optional.empty();
    }
}
