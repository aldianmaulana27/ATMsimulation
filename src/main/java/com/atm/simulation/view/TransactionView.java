package com.atm.simulation.view;

import com.atm.simulation.entity.Account;
import com.atm.simulation.service.AccountService;
import com.atm.simulation.service.BalanceService;
import com.atm.simulation.util.InputUtil;
import com.atm.simulation.util.ValidationUtil;

import java.util.Date;
import java.util.Random;

public class TransactionView {

    private static AccountService accountService;
    private static BalanceService balanceService;

    public TransactionView(AccountService accountService) {
        TransactionView.accountService = accountService;
    }

    public TransactionView(BalanceService balanceService) {
        TransactionView.balanceService = balanceService;
    }

    public void showMenu() {
    }
    public void showMenu(Integer accNumb) {
        transactionScreen(accNumb);
    }


    public static void transactionScreen(Integer accNumb) {

        System.out.println("""
                1. Withdraw
                2. Fund Transfer
                3. Exit""");

        var input = InputUtil.inputString("Please choose option[3]: ");

        if (input.equals("1")) {
            withdrawScreen(accNumb);
        } else if (input.equals("2")) {
            fundScreen(accNumb);
        } else if (input.equals("3") || input.isEmpty() || input.isBlank()) {
            WelcomeView.welcomeScreen();
        } else {
            transactionScreen(accNumb);
        }
    }

    public static void withdrawScreen(Integer accNumb) {
        System.out.println("""
                1. $10
                2. $50
                3. $100
                4. Other
                5. Back""");
        var input = InputUtil.inputString("Please choose option[5]: ");

        switch (input) {
            case "1", "2", "3" -> summaryScreen(input, accNumb);
            case "4" -> otherScreen(accNumb);
            case "5" -> transactionScreen(accNumb);
        }
    }

    public static void summaryScreen(String input, Integer accNumb) {
        Account account = accountService.getAccount(accNumb);
        Integer balance = account.getBalance().getBalance();

        int withdraw;

        switch (input) {
            case "1" -> withdraw = 10;
            case "2" -> withdraw = 50;
            case "3" -> withdraw = 100;
            default -> {
                withdraw = InputUtil.integerConvert(input);
//                    balance = withdraw;
            }
        }
        if (withdraw > balance) {
            System.out.println("Insufficient balance $" + balance);
            transactionScreen(accNumb);
        } else {
            Integer currentBalance = balance - withdraw;
            balanceService.updateBalance(account.getAccountNumber(), currentBalance);

            System.out.println("Summary");
            System.out.println("Date : " + new Date());
            System.out.println("withdraw : $" + withdraw);
            System.out.println(("Balance : $" + currentBalance));
            System.out.println("1. Transaction \n" +
                    "2. Exit");
            account.getBalance().setBalance(currentBalance);
            var input2 = InputUtil.inputString("Choose option[2]: ");

            if (input2.equals("1")) {
                transactionScreen(accNumb);
            } else {
                WelcomeView.welcomeScreen();
            }
        }
    }

    public static void otherScreen(Integer accNumb) {
        Account account = accountService.getAccount(accNumb);
        System.out.println("Other Withdraw");
        var input = InputUtil.inputString("Enter amount to withdraw: ");
        boolean check = ValidationUtil.isNumeric(input);
        if (!check) {
            System.out.println("Invalid ammount");
            transactionScreen(accNumb);
        }
        int inputInt = Integer.parseInt(input);
        if (inputInt > 1000) {
            System.out.println("Maximum amount to withdraw is $1000");
            transactionScreen(accNumb);
        }
        if (inputInt % 10 != 0) {
            System.out.println("Invalid ammount");
            transactionScreen(accNumb);
        }
        if (inputInt > account.getBalance().getBalance()) {
            System.out.println("Insufficient balance $" + inputInt);
            transactionScreen(accNumb);
        }
        summaryScreen(input, accNumb);

    }

    public static void fundScreen(Integer accNumb) {
        Account account = accountService.getAccount(accNumb);
        Account accountDest = new Account();
        //step 1
        System.out.println("Please enter destination account and \n" +
                "press enter to continue or");
        var input = InputUtil.inputString("press cancel (Esc) to go back to Transaction:");
//        int keyCode = KeyEvent.VK_ESCAPE;

//        if (keyCode == KeyEvent.VK_ESCAPE) {
//            transactionScreen();
//        }

        //step 2 amount
        System.out.println("Please enter transfer amount and press enter to continue or ");
        var amount = InputUtil.inputString("press enter to go back to Transaction:");
        if (amount.isBlank() || amount.isEmpty()) {
            transactionScreen(accNumb);
        }

        System.out.println("Reference Number: (This is an autogenerated random 6 digits number)");
        var input3 = InputUtil.inputString("Press enter to continue or click 'x' Enter to go back to Transaction: ");

        if (input3.equalsIgnoreCase("x")) {
            transactionScreen(accNumb);
        } else if (!input3.isEmpty() || !input3.isBlank()) {
            System.out.println("Invalid Reference Number");
            fundScreen(accNumb);
        }

        Random random = new Random();
        int randomNumber = random.nextInt(900000) + 100000;


        System.out.println("Transfer Confirmation\n" +
                "Destination Account : " + input + "\n" +
                "Transfer Amount     : $" + amount + "\n" +
                "Reference Number    : " + randomNumber + "\n" +
                "\n" +
                "1. Confirm Trx\n" +
                "2. Cancel Trx");

        var input4 = InputUtil.inputString("Choose option[2]:");

        if (input4.equals("1")) {
            //validate step 1
            if (input.equals("Esc")) {
                transactionScreen(accNumb);
            }
            //numeric validation
            if (!ValidationUtil.isNumeric(input)) {
                System.out.println("Invalid account");
                fundScreen(accNumb);
            }
            accountDest = accountService.getAccount(InputUtil.integerConvert(input));
            //validate account
            if (accountDest == null) {
                System.out.println("Invalid account");
                fundScreen(accNumb);
            }

            //validate step 2
            boolean check = ValidationUtil.isNumeric(amount);
            if (!check) {
                System.out.println("Invalid amount");
                fundScreen(accNumb);
            }

            if (amount.isBlank() || amount.isEmpty()) {
                transactionScreen(accNumb);
            }
            //validate
            if (Integer.parseInt(amount) > 1000) {
                System.out.println("Maximum amount to transfer is $1000");
                fundScreen(accNumb);
            } else if (Integer.parseInt(amount) < 1) {
                System.out.println("Minimum amount to transfer is $1");
                fundScreen(accNumb);
            }
            if (!ValidationUtil.isNumeric(amount)) {
                System.out.println("Invalid account");
                fundScreen(accNumb);
            }

            if (Integer.parseInt(amount) > account.getBalance().getBalance()) {
                System.out.println("Insufficient balance $" + amount);
                fundScreen(accNumb);
            } else {
                balanceService.updateBalance(accNumb, account.getBalance().getBalance() - Integer.parseInt(amount));
                assert accountDest != null;
                balanceService.updateBalance(InputUtil.integerConvert(input), Integer.parseInt(amount) + accountDest.getBalance().getBalance());
                fundSummaryScreen(Integer.parseInt(amount), randomNumber, InputUtil.integerConvert(input), accNumb);
            }
        } else if (input4.equals("2")) {
            fundScreen(accNumb);
        }

    }
    public static void fundSummaryScreen(Integer trfAmount, Integer refNum, Integer accDest, Integer accNumb){
        Account account = accountService.getAccount(accNumb);
        Account accountDest = accountService.getAccount(accDest);

        System.out.println("Fund Transfer Summary\n" +
                "Destination Account : " + accountDest.getAccountNumber() + "\n" +
                "Transfer Amount     : $" + trfAmount + "\n" +
                "Reference Number    : " + refNum + "\n" +
                "Balance             : $" + account.getBalance().getBalance() + "\n" +
                "\n" +
                "1. Transaction\n" +
                "2. Exit");
        var input = InputUtil.inputString("Choose option[2]");
        if (input.equals("1")) {
            transactionScreen(accNumb);
        } else if (input.equals("2")) {
            WelcomeView.welcomeScreen();
        }
    }

}