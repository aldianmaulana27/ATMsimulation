package com.atm.simulation.view;


import com.atm.simulation.entity.User;
import com.atm.simulation.service.UserService;
import com.atm.simulation.util.InputUtil;
import com.atm.simulation.util.ValidationUtil;

import java.util.Date;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class UserView {

    private UserService userService;
//    @Autowired
//    private ValidationUtil validationUtil;
    private User user;
    private User userDest;
    public UserView(UserService userService){
        this.userService = userService;
    }

    public void showUser(){
        while (true) {
            userService.showAllUser();

            System.out.println("Menu");
            welcomeScreen();
        }

    }

    public void welcomeScreen(){
        var input = InputUtil.inputString("Enter Account Number : ");
        var inputPin = InputUtil.inputString("Enter PIN : ");

        boolean check = ValidationUtil.checkLength(input, inputPin);
        boolean check2 = ValidationUtil.isNumber(input, inputPin);

        if (check2 && check){
            for (User account : userService.showAllUser()){
                if (parseInt(input) == account.getAccount().getAccountNumber() && account.getAccount().getPin().equals(inputPin)){
                    user = account;
                    transactionScreen();
                }
            }
            if(user == null){
                System.out.println("Invalid Account Number/PIN");
            }
        }
    }

    public void transactionScreen(){
        System.out.println("""
                1. Withdraw
                2. Fund Transfer
                3. Exit""");

        var input = InputUtil.inputString("Please choose option[3]: ");

        if(input.equals("1")){
            withdrawScreen();
        } else if (input.equals("2")) {
            fundScreen();
        } else if (input.equals("3")) {
            welcomeScreen();
        }else if(input.isEmpty() || input.isBlank()){
            welcomeScreen();
        }else{
            transactionScreen();
        }
    }
    public void withdrawScreen(){
        System.out.println("""
                1. $10
                2. $50
                3. $100
                4. Other
                5. Back""");
        var input = InputUtil.inputString("Please choose option[5]: ");

        switch (input) {
            case "1", "2", "3" -> summaryScreen(input);
            case "4" -> otherScreen();
            case "5" -> transactionScreen();
        }

    }
    public void fundScreen(){
        //step 1
        System.out.println("Please enter destination account and \n" +
                "press enter to continue or");
        var input = InputUtil.inputString("press cancel (Esc) to go back to Transaction:");
//        int keyCode = KeyEvent.VK_ESCAPE;

//        if (keyCode == KeyEvent.VK_ESCAPE) {
//            transactionScreen();
//        }
        if(input.equals("Esc")){
            transactionScreen();
        }
        //numeric validation
        if(!ValidationUtil.isNumeric(input)){
            System.out.println("Invalid account");
            fundScreen();
        }

        for (User dest : userService.showAllUser()){
            if(Integer.parseInt(input) == dest.getAccount().getAccountNumber()){
                userDest = dest;
            }
        }
        //validate account
        if(userDest == null) {
            System.out.println("Invalid account");
            fundScreen();
        }

        //step 2 amount
        System.out.println("Please enter transfer amount and press enter to continue or ");
        var amount = InputUtil.inputString("press enter to go back to Transaction:");
        if(amount.isBlank() || amount.isEmpty()){
            transactionScreen();
        }
        //validate
        if(Integer.parseInt(amount) > 1000){
            System.out.println("Maximum amount to transfer is $1000");
            fundScreen();
        }else if(Integer.parseInt(amount) < 1){
            System.out.println("Minimum amount to transfer is $1");
            fundScreen();
        }
        if(!ValidationUtil.isNumeric(amount)){
            System.out.println("Invalid account");
            fundScreen();
        }

        Random random = new Random();

        int randomNumber = random.nextInt(900000) + 100000;
        System.out.println("Reference Number: " + randomNumber);
        var input3 = InputUtil.inputString("ress enter to continue or click 'x' Enter to go back to Transaction: ");

        if(input3.equalsIgnoreCase("x")){
            transactionScreen();
        }


        System.out.println("Transfer Confirmation\n" +
                "Destination Account : "+ userDest.getAccount().getAccountNumber() +"\n" +
                "Transfer Amount     : $"+amount+"\n" +
                "Reference Number    : "+randomNumber+"\n" +
                "\n" +
                "1. Confirm Trx\n" +
                "2. Cancel Trx");

        var input4 = InputUtil.inputString("Choose option[2]:");

        if(input4.equals("1")){
            if(Integer.parseInt(amount) > user.getAccount().getBalance().getBalance()){
                System.out.println("Insufficient balance $"+amount);
                fundScreen();
            }else{
                user.getAccount().getBalance().setBalance(user.getAccount().getBalance().getBalance() - Integer.parseInt(amount) );
                userDest.getAccount().getBalance().setBalance(Integer.parseInt(amount)+userDest.getAccount().getBalance().getBalance());
                fundSummaryScreen(Integer.parseInt(amount), randomNumber);
            }
        } else if (input4.equals("2")) {
            fundScreen();
        }

    }

    public void fundSummaryScreen(Integer trfAmount, Integer refNum){
        System.out.println("Fund Transfer Summary\n" +
                "Destination Account : "+userDest.getAccount().getAccountNumber()+"\n" +
                "Transfer Amount     : $"+trfAmount+"\n" +
                "Reference Number    : "+refNum+"\n" +
                "Balance             : $"+user.getAccount().getBalance().getBalance()+"\n" +
                "\n" +
                "1. Transaction\n" +
                "2. Exit");
        var input = InputUtil.inputString("Choose option[2]");
        if(input.equals("1")){
            transactionScreen();
        } else if (input.equals("2")) {
            welcomeScreen();
        }
    }

    public void summaryScreen(String input){
        Integer balance = user.getAccount().getBalance().getBalance();

        int withdraw ;
        switch (input) {
            case "1" -> withdraw = 10;
            case "2" -> withdraw = 50;
            case "3" -> withdraw = 100;
            default -> {
                withdraw = InputUtil.integerConvert(input);
                balance = withdraw;
            }
        }
        if(withdraw > balance){
            System.out.println("Insufficient balance $"+balance);
            transactionScreen();
        }else {
            Integer currentBalance = balance - withdraw;

            System.out.println("Summary");
            System.out.println("Date : " + new Date());
            System.out.println("withdraw : $" + withdraw);
            System.out.println(("Balance : $" + currentBalance));
            System.out.println("1. Transaction \n" +
                    "2. Exit");
            user.getAccount().getBalance().setBalance(currentBalance);
//            System.out.println(user.getBalance().getBalance());
            var input2 = InputUtil.inputString("Choose option[2]: ");

            if(input2.equals("1")){
                transactionScreen();
            }else if(input2.equals("2")) {
                welcomeScreen();
            }else {
                welcomeScreen();
            }
        }

    }
    public void otherScreen(){
        System.out.println("Other Withdraw");
        var input = InputUtil.inputString("Enter amount to withdraw: ");
        int inputInt = Integer.parseInt(input);
        boolean check = ValidationUtil.isNumeric(input);
        if(!check || inputInt % 10 != 0){
            System.out.println("Invalid ammount");
        }
        if(inputInt > 1000){
            System.out.println("Maximum amount to withdraw is $1000");
        }
        if(inputInt > user.getAccount().getBalance().getBalance()){
            System.out.println("Insufficient balance $"+inputInt);
            transactionScreen();
        }
        summaryScreen(input);

    }

}
