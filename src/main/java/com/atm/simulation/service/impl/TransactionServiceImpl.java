package com.atm.simulation.service.impl;

import com.atm.simulation.entity.Account;
import com.atm.simulation.service.AccountService;
import com.atm.simulation.service.TransactionService;
import com.atm.simulation.util.InputUtil;
import com.atm.simulation.util.ValidationUtil;

import javax.swing.*;

public class TransactionServiceImpl extends JTextField implements TransactionService {

    private AccountService accountService;
    public TransactionServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public Integer withdraw(Integer accNo, Integer amount) {
        Integer balance = accountService.getAccount(accNo).getBalance().getBalance();
        if (amount > balance) {
            throw new RuntimeException("Insufficient balance $" + balance);
        }else {
            accountService.updateAccountBalance(accNo,balance-amount);
            return accountService.getAccount(accNo).getBalance().getBalance();
        }
    }

    @Override
    public Integer withdraw(Integer accNo, String amount) {
        boolean check = ValidationUtil.isNumeric(amount);
        if (!check) {
            throw new RuntimeException("Invalid ammount");
        }
        int withdraw = Integer.parseInt(amount);
        if (withdraw > 1000) {
            throw new RuntimeException("Maximum amount to withdraw is $1000");
        }
        if (withdraw % 10 != 0) {
            throw new RuntimeException("Invalid ammount");
        }
        Integer balance = accountService.getAccount(accNo).getBalance().getBalance();
        if (withdraw > balance) {
            throw new RuntimeException("Insufficient balance $" + withdraw);
        } else {
            accountService.updateAccountBalance(accNo,balance-withdraw);
            return accountService.getAccount(accNo).getBalance().getBalance();
        }
    }

    @Override
    public void fundTransaction(Integer accNo,String accountDest, String amount, Integer random) {
        accountDest = accountDest.replace("\n","");
        //numeric validation
        if (!ValidationUtil.isNumeric(accountDest)) {
            throw new RuntimeException("Invalid account");
        }
        Account accDest = accountService.getAccount(InputUtil.integerConvert(accountDest));
        //validate account
        if (accDest == null) {
            throw new RuntimeException("Invalid account");
        }

        //validate step 2
        boolean check = ValidationUtil.isNumeric(amount);
        if (!check) {
            throw new RuntimeException("Invalid account");
        }

        //validate
        if (Integer.parseInt(amount) > 1000) {
            throw new RuntimeException("Maximum amount to transfer is $1000");
        } else if (Integer.parseInt(amount) < 1) {
            throw new RuntimeException("Minimum amount to transfer is $1");
        }
        Integer balance = accountService.getAccount(accNo).getBalance().getBalance();
        if (Integer.parseInt(amount) > balance) {
            throw new RuntimeException("Insufficient balance $" + amount);
        } else {
            accountService.updateAccountBalance(accNo, balance - Integer.parseInt(amount));
            accountService.updateAccountBalance(InputUtil.integerConvert(accountDest), Integer.parseInt(amount) + (accDest != null ? accDest.getBalance().getBalance() : null));
        }

    }

}
