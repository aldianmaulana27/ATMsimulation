package com.atm.simulation.service;

import com.atm.simulation.entity.Account;
import com.atm.simulation.repository.AccountRepository;
import com.atm.simulation.repository.impl.AccountRepositoryImpl;
import com.atm.simulation.service.impl.AccountServiceImpl;
import com.atm.simulation.util.ValidationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;

import java.util.Optional;

public class AccountServiceTest {

    private ValidationUtil validationUtil = new ValidationUtil();
    private AccountRepository accountRepository = new AccountRepositoryImpl();
    private AccountService accountService = new AccountServiceImpl(accountRepository,validationUtil);

    @BeforeEach
    void addAccount(){
        accountRepository.addAccount();
    }

    @Test
    void testGetAccount(){
        Account account = accountService.getAccount(112233);
        System.out.println(account.toString());
    }

    @Test
    void loginTest(){
        Optional<Account> account = accountService.login("112233","012108");
        if (account.isPresent()){
            System.out.println(account.get().getAccountNumber());
        }else {
            System.out.println("Invalid");
        }
    }




}
