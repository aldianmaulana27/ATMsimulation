package com.atm.simulation.view;

import com.atm.simulation.entity.Account;
import com.atm.simulation.service.AccountService;
import com.atm.simulation.service.AuthenticationService;
import com.atm.simulation.service.FileReaderService;
import com.atm.simulation.util.InputUtil;

import java.util.List;
import java.util.Optional;

public class WelcomeView {

    private  AccountService accountService;
    private  TransactionView transactionView;
    private InputUtil inputUtil;
    private AuthenticationService authenticationService;
    private FileReaderService fileReaderService;

    public WelcomeView(AccountService accountService, AuthenticationService authenticationService, FileReaderService fileReaderService, TransactionView transactionView, InputUtil inputUtil){
        this.accountService = accountService;
        this.authenticationService = authenticationService;
        this.fileReaderService = fileReaderService;
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

    public void uploadFIle(String path){
        System.out.println("read file from this path : "+path);
        try {
            List<Account> data = fileReaderService.uploadDoc(path);
            accountService.addAccountFromDoc(data);
            welcomeScreen();
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.exit(0);
        }

    }
    public void welcomeScreen(){
        System.out.println("Menu");
        var input = inputUtil.inputString("Enter Account Number : ");
        var inputPin = inputUtil.inputString("Enter PIN : ");
            Optional<Account> account = authenticationService.login(input,inputPin);
            if (account.isPresent()){
                transactionView.showMenu(account.get());
            }else{
                System.out.println("Invalid Account Number/PIN");
                welcomeScreen();
            }

    }
}
