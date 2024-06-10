package com.atm.simulation.service.impl;

import com.atm.simulation.entity.Account;
import com.atm.simulation.service.AccountService;
import com.atm.simulation.service.TransactionService;
import com.atm.simulation.util.InputUtil;
import com.atm.simulation.util.ValidationUtil;
import com.atm.simulation.view.TransactionView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;

public class TransactionServiceImpl extends JTextField implements TransactionService {
    @Autowired
    TransactionView transactionView;
    private AccountService accountService;
    public TransactionServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public Integer withdraw(Integer accNo, Integer amount) {
        Integer balance = accountService.getAccount(accNo).getBalance().getBalance();
        if (amount > balance) {
            System.out.println("Insufficient balance $" + balance);
            transactionView.transactionScreen(accNo);
        }else {
            accountService.updateAccountBalance(accNo,balance-amount);
            return accountService.getAccount(accNo).getBalance().getBalance();
        }
        return null;
    }

    @Override
    public Integer withdraw(Integer accNo, String amount) {
        boolean check = ValidationUtil.isNumeric(amount);
        if (!check) {
            System.out.println("Invalid ammount");
            transactionView.transactionScreen(accNo);
        }
        int withdraw = Integer.parseInt(amount);
        if (withdraw > 1000) {
            System.out.println("Maximum amount to withdraw is $1000");
            transactionView.transactionScreen(accNo);
        }
        if (withdraw % 10 != 0) {
            System.out.println("Invalid ammount");
            transactionView.transactionScreen(accNo);
        }
        Integer balance = accountService.getAccount(accNo).getBalance().getBalance();
        if (withdraw > balance) {
            System.out.println("Insufficient balance $" + withdraw);
            transactionView.transactionScreen(accNo);
        } else {
            accountService.updateAccountBalance(accNo,balance-withdraw);
            return accountService.getAccount(accNo).getBalance().getBalance();
        }
        return null;
    }

    @Override
    public void fundTransaction(Integer accNo,String accountDest, String amount, Integer random) {
        accountDest = accountDest.replace("\n","");
        //numeric validation
        if (!ValidationUtil.isNumeric(accountDest)) {
            System.out.println("Invalid account");
            transactionView.fundScreen(accNo);
        }
        Account accDest = accountService.getAccount(InputUtil.integerConvert(accountDest));
        //validate account
        if (accDest == null) {
            System.out.println("Invalid account");
            transactionView.fundScreen(accNo);
        }

        //validate step 2
        boolean check = ValidationUtil.isNumeric(amount);
        if (!check) {
            System.out.println("Invalid amount");
            transactionView.fundScreen(accNo);
        }

        if (amount.isBlank() || amount.isEmpty()) {
            transactionView.transactionScreen(accNo);
        }
        //validate
        if (Integer.parseInt(amount) > 1000) {
            System.out.println("Maximum amount to transfer is $1000");
            transactionView.fundScreen(accNo);
        } else if (Integer.parseInt(amount) < 1) {
            System.out.println("Minimum amount to transfer is $1");
            transactionView.fundScreen(accNo);
        }
        Integer balance = accountService.getAccount(accNo).getBalance().getBalance();
        if (Integer.parseInt(amount) > balance) {
            System.out.println("Insufficient balance $" + amount);
            transactionView.fundScreen(accNo);
        } else {
            accountService.updateAccountBalance(accNo, balance - Integer.parseInt(amount));
            accountService.updateAccountBalance(InputUtil.integerConvert(accountDest), Integer.parseInt(amount) + (accDest != null ? accDest.getBalance().getBalance() : null));
            TransactionView.fundSummaryScreen(Integer.parseInt(amount), random, InputUtil.integerConvert(accountDest), accNo);
        }

    }

}
