package com.atm.simulation.view;

import com.atm.simulation.entity.Account;
import com.atm.simulation.service.AccountService;
import com.atm.simulation.util.InputUtil;

import java.util.Optional;

public class WelcomeView {

    private  AccountService accountService;
    private  TransactionView transactionView;
    private InputUtil inputUtil;

    public WelcomeView(AccountService accountService, TransactionView transactionView, InputUtil inputUtil){
        this.accountService = accountService;
        this.transactionView = transactionView;
        this.inputUtil = inputUtil;
        this.transactionView.setParentView(this);
    }

    public void showMenu(){
        while (true) {
            welcomeScreen();
        }
    }

    public void uploadFIle(String path){
        System.out.println("read file from this path : "+path);
        try {
            String data = inputUtil.uploadDoc(path);
            accountService.addAccountFromDoc(data);
            welcomeScreen();
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.exit(0);
        }

    }
    public void welcomeScreen(){
        for (Account account : accountService.showAllAccount()){
            System.out.println(account.toString());
        }
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
