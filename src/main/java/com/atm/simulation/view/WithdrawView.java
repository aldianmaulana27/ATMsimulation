package com.atm.simulation.view;

import com.atm.simulation.service.AccountService;
import com.atm.simulation.service.TransactionService;
import com.atm.simulation.util.InputUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WithdrawView {

    private AccountService accountService;
    private TransactionView transactionView;
    private WelcomeView welcomeView;
    private TransactionService transactionService;
    private InputUtil inputUtil;

    public WithdrawView(AccountService accountService,InputUtil inputUtil){
        this.accountService = accountService;
        this.inputUtil = inputUtil;
    }

    public void setParentView(TransactionView transactionView, WelcomeView welcomeView, TransactionService transactionService) {
        this.transactionView = transactionView;
        this.welcomeView = welcomeView;
        this.transactionService = transactionService;
    }

    public void withdrawScreen(Integer accNumb) {
        System.out.println("""
                1. $10
                2. $50
                3. $100
                4. Other
                5. Back""");
        var input = inputUtil.inputString("Please choose option[5]: ");

        switch (input) {
            case "1", "2", "3" -> summaryScreen(input, accNumb);
            case "4" -> otherScreen(accNumb);
            case "5" -> transactionView.transactionScreen(accNumb);
        }
    }

    public void summaryScreen(String input, Integer accNumb) {
        int withdraw;
        switch (input) {
            case "1" -> withdraw = 10;
            case "2" -> withdraw = 50;
            case "3" -> withdraw = 100;
            default -> withdraw = Integer.parseInt(input);
        }

        Integer currentBalance = 0;
        if(input.length()!=1){
            currentBalance = accountService.getAccount(accNumb).getBalance().getBalance();
        }else{
            try {
                currentBalance = transactionService.withdraw(accNumb,withdraw);
            }catch (Exception e){
                System.out.println(e.getMessage());
                transactionView.transactionScreen(accNumb);
            }
        }
        System.out.println("Summary");
        System.out.println("Date : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a")));
        System.out.println("withdraw : $" + withdraw);
        System.out.println(("Balance : $" + currentBalance));
        System.out.println("1. Transaction \n" +
                "2. Exit");
        var input2 = inputUtil.inputString("Choose option[2]: ");

        if (input2.equals("1")) {
            transactionView.transactionScreen(accNumb);
        } else {
            welcomeView.welcomeScreen();
        }
    }

    public void otherScreen(Integer accNumb) {
        System.out.println("Other Withdraw");
        var input = inputUtil.inputString("Enter amount to withdraw: ");
        try {
            transactionService.withdraw(accNumb,input);
        } catch (Exception e){
            System.out.println(e.getMessage());
            transactionView.transactionScreen(accNumb);
        }

        summaryScreen(input, accNumb);
    }
}
