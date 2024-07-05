package com.atm.simulation.view;

import com.atm.simulation.entity.Account;
import com.atm.simulation.entity.TransactionHistory;
import com.atm.simulation.service.AccountService;
import com.atm.simulation.service.TransactionService;

import java.util.List;

public class TransactionHistoryView {
    private TransactionService transactionService;

    public TransactionHistoryView(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    public void history(Account account) {
        System.out.println("============ Transaction History ============");
        System.out.println("============ Account Number "+ account.getAccountNumber() +" ============");
        List<TransactionHistory> listTransactionHistory = transactionService.getAllTransactionHistory(account.getAccountNumber());
        System.out.println(listTransactionHistory.toString());

    }
}
