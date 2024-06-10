package com.atm.simulation.view;

import com.atm.simulation.entity.Account;
import com.atm.simulation.service.AccountService;
import com.atm.simulation.util.InputUtil;
import com.atm.simulation.util.ValidationUtil;

import java.util.Optional;

public class WelcomeView {

    private static AccountService accountService;
    public WelcomeView(AccountService accountService){
        WelcomeView.accountService = accountService;
    }

    public void showMenu(){
        while (true) {
            accountService.showAllAccount();
            welcomeScreen();
        }

    }

    public static void welcomeScreen(){
        System.out.println("Menu");
        var input = InputUtil.inputString("Enter Account Number : ");
        var inputPin = InputUtil.inputString("Enter PIN : ");


            Optional<Account> account = accountService.login(input,inputPin);
            if (account.isPresent()){
                TransactionView transactionView = new TransactionView(accountService);
                transactionView.showMenu(account.get().getAccountNumber());
            }else{
                System.out.println("Invalid Account Number/PIN");
                welcomeScreen();
            }

    }
}
