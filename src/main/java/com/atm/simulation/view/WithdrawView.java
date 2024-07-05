package com.atm.simulation.view;

import com.atm.simulation.entity.Account;
import com.atm.simulation.service.TransactionService;
import com.atm.simulation.util.InputUtil;
import com.atm.simulation.util.ValidationUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WithdrawView {

    private TransactionView transactionView;
    private WelcomeView welcomeView;
    private TransactionService transactionService;
    private InputUtil inputUtil;
    private ValidationUtil validationUtil;

    public WithdrawView(InputUtil inputUtil, ValidationUtil validationUtil){
        this.inputUtil = inputUtil;
        this.validationUtil = validationUtil;
    }

    public void setParentView(TransactionView transactionView, WelcomeView welcomeView, TransactionService transactionService) {
        this.transactionView = transactionView;
        this.welcomeView = welcomeView;
        this.transactionService = transactionService;
    }

    public void withdrawScreen(Account account) {
        System.out.println("""
                1. $10
                2. $50
                3. $100
                4. Other
                5. Back""");
        var input = inputUtil.inputString("Please choose option[5]: ");

        switch (input) {
            case "1", "2", "3" -> summaryScreen(input, account);
            case "4" -> otherScreen(account);
            case "5" -> transactionView.transactionScreen(account);
        }
    }

    public void summaryScreen(String input, Account account) {
        int withdraw;
        switch (input) {
            case "1" -> withdraw = 10;
            case "2" -> withdraw = 50;
            case "3" -> withdraw = 100;
            default -> withdraw = Integer.parseInt(input);
        }

        Integer currentBalance = 0;
            try {
                currentBalance = transactionService.withdraw(account.getAccountNumber(),withdraw);
            }catch (Exception e){
                System.out.println(e.getMessage());
                transactionView.transactionScreen(account);
            }
        System.out.println("Summary");
        System.out.println("Date : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a")));
        System.out.println("withdraw : $" + withdraw);
        System.out.println(("Balance : $" + currentBalance));
        System.out.println("1. Transaction \n" +
                "2. Exit");
        var input2 = inputUtil.inputString("Choose option[2]: ");

        if (input2.equals("1")) {
            transactionView.transactionScreen(account);
        } else {
            welcomeView.welcomeScreen();
        }
    }

    public void otherScreen(Account account) {
        System.out.println("Other Withdraw");
        var input = inputUtil.inputString("Enter amount to withdraw: ");
        boolean check = validationUtil.isNumeric(input);
        if (!check) {
            System.out.println("Invalid ammount");
            transactionView.transactionScreen(account);
        }
        summaryScreen(input,account);
    }
}
