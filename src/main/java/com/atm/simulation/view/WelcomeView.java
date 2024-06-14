package com.atm.simulation.view;

import com.atm.simulation.entity.Account;
import com.atm.simulation.service.AccountService;
import com.atm.simulation.util.InputUtil;

import java.util.Optional;

public class WelcomeView {

    private  AccountService accountService;
    private  TransactionView transactionView;
    private InputUtil inputUtil;

    public WelcomeView(AccountService accountService, TransactionView transactionView,InputUtil inputUtil){
        this.accountService = accountService;
        this.transactionView = transactionView;
        this.inputUtil = inputUtil;
        this.transactionView.setParentView(this);
    }

    public void showMenu(){
        while (true) {
            accountService.showAllAccount();
            welcomeScreen();
        }
    }

    public void welcomeScreen(){
        System.out.println("Menu");
        var input = inputUtil.inputString("Enter Account Number : ");
        var inputPin = inputUtil.inputString("Enter PIN : ");
            Optional<Account> account = accountService.login(input,inputPin);
            if (account.isPresent()){
                transactionView.showMenu(account.get().getAccountNumber());
            }else{
                System.out.println("Invalid Account Number/PIN");
                welcomeScreen();
            }

    }
}
