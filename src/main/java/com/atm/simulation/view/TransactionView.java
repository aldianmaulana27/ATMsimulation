package com.atm.simulation.view;

import com.atm.simulation.service.TransactionService;
import com.atm.simulation.util.InputUtil;

public class TransactionView {
    private WelcomeView welcomeView;
    private WithdrawView withdrawView;
    private TransferView transferView;
    private TransactionService transactionService;

    public TransactionView(WithdrawView withdrawView, TransferView transferView, TransactionService transactionService) {
        this.withdrawView = withdrawView;
        this.transferView = transferView;
        this.transactionService = transactionService;
    }

    public void showMenu(Integer accNumb) {
        transactionScreen(accNumb);
    }

    public void transactionScreen(Integer accNumb) {
        System.out.println("""
                1. Withdraw
                2. Fund Transfer
                3. Exit""");

        System.out.println("Please choose option[3]: \n");
        var input = InputUtil.inputString("");

        if (input.equals("1")) {
            withdrawView.withdrawScreen(accNumb);
        } else if (input.equals("2")) {
            transferView.fundScreen(accNumb);
        } else if (input.equals("3") || input.isEmpty() || input.isBlank()) {
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