package com.atm.simulation.service.impl;

import com.atm.simulation.entity.Account;
import com.atm.simulation.entity.TransactionHistory;
import com.atm.simulation.enums.TransactionTypes;
import com.atm.simulation.repository.TransactionHistoryRepository;
import com.atm.simulation.service.AccountService;
import com.atm.simulation.service.TransactionService;
import com.atm.simulation.util.ValidationUtil;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.List;

public class TransactionServiceImpl extends JTextField implements TransactionService {

    private AccountService accountService;
    private ValidationUtil validationUtil;
    private TransactionHistoryRepository transactionHistoryRepository;
    public TransactionServiceImpl(TransactionHistoryRepository transactionHistoryRepository,AccountService accountService,ValidationUtil validationUtil) {
        this.transactionHistoryRepository = transactionHistoryRepository;
        this.accountService = accountService;
        this.validationUtil = validationUtil;
    }

    @Override
    public Integer withdraw(Integer accNo, Integer amount) {
        Account account = accountService.getAccount(accNo);
        account.decreasBalance(amount,true);
        addHistory(new TransactionHistory(accNo, LocalDateTime.now(), TransactionTypes.WITHDRAW,amount));
        return account.getBalance();
    }


    @Override
    public void fundTransaction(Account account, String accountDest, String amount, int randomNumber) {

        accountDest = accountDest.replace("\n","");
        //numeric validation
        if (!validationUtil.isNumeric(accountDest)) {
            throw new RuntimeException("Invalid account");
        }
        //validate step 2
        boolean check = validationUtil.isNumeric(amount);
        if (!check) {
            throw new RuntimeException("Invalid account");
        }

        Account accDest = accountService.getAccount(Integer.parseInt(accountDest));
        if (accDest != null) {
            accDest.fundBalance(account,amount);
        }else {
            throw new RuntimeException("Invalid account");
        }
        account.decreasBalance(Integer.parseInt(amount), false);
        addHistory(new TransactionHistory(account.getAccountNumber(), LocalDateTime.now(), TransactionTypes.TRANSFER,Integer.parseInt(amount),accDest.getAccountNumber(),randomNumber));
        //this for add to receive account2
        addHistory(new TransactionHistory(accDest.getAccountNumber(), LocalDateTime.now(), TransactionTypes.DEPOSIT,Integer.parseInt(amount),account.getAccountNumber(),randomNumber));
    }

    @Override
    public void addHistory(TransactionHistory transactionHistory) {
        transactionHistoryRepository.addTransactionHistory(transactionHistory);
    }

    @Override
    public List<TransactionHistory> getAllTransactionHistory(Integer accNo) {
       return transactionHistoryRepository.getAllTransactionHistory(accNo, 10);
    }
}
