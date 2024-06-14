package com.atm.simulation.view;

import com.atm.simulation.service.TransactionService;
import com.atm.simulation.util.InputUtil;

public class TransactionView {
    private WelcomeView welcomeView;
    private WithdrawView withdrawView;
    private TransferView transferView;
    private TransactionHistoryView transactionHistoryView;
    private TransactionService transactionService;
    private InputUtil inputUtil;

    public TransactionView(WithdrawView withdrawView, TransferView transferView,TransactionHistoryView transactionHistoryView, TransactionService transactionService,InputUtil inputUtil) {
        this.withdrawView = withdrawView;
        this.transferView = transferView;
        this.transactionHistoryView = transactionHistoryView;
        this.transactionService = transactionService;
        this.inputUtil = inputUtil;
    }

    public void showMenu(Integer accNumb) {
        transactionScreen(accNumb);
    }

    public void transactionScreen(Integer accNumb) {
        System.out.println("""
                1. Withdraw
                2. Fund Transfer
                3. Transaction History
                4. Exit""");

        System.out.println("Please choose option[3]: \n");
        var input = inputUtil.inputString("");

        if (input.equals("1")) {
            withdrawView.withdrawScreen(accNumb);
        } else if (input.equals("2")) {
            transferView.fundScreen(accNumb);
        } else if (input.equals("3")) {
            transactionHistoryView.showListHistory(accNumb);
        } else if (input.equals("4") || input.isEmpty() || input.isBlank()) {
            welcomeView.welcomeScreen();
        } else {
            transactionScreen(accNumb);
        }
    }

    public void setParentView(WelcomeView welcomeView) {
        this.welcomeView = welcomeView;
        this.withdrawView.setParentView(this,welcomeView, transactionService);
        this.transferView.setParentView(this,welcomeView, transactionService);
    }
}