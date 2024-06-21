package com.atm.simulation.view;

import com.atm.simulation.entity.TransactionHistory;
import com.atm.simulation.service.TransactionService;

import java.util.List;

public class TransactionHistoryView {
    private TransactionService transactionService;
    public TransactionHistoryView(TransactionService transactionService){
        this.transactionService = transactionService;
    }
    public void showListHistory(Integer accNo){
        List<TransactionHistory> listData = transactionService.getAllListByAccNo(accNo);
        System.out.println("Transaction History Account "+ accNo + " \n");
        for (TransactionHistory transactionHistory : listData){
            System.out.println(transactionHistory.toString());
        }

    }
}
