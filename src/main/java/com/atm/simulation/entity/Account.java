package com.atm.simulation.entity;

public class Account {

    private Integer accountNumber;
    private String pin;
    private String userName;
    private Balance balance;

    public Account(){};
    public Account(Integer accountNumber, String pin, String userName, Balance balance){
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.userName = userName;
        this.balance = balance;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(Integer accountNumber){
        this.accountNumber = accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }
}
