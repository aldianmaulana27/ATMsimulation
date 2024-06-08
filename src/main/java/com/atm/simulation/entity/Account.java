package com.atm.simulation.entity;

public class Account {

    private Integer accountNumber;
    private String pin;
    private DetailAccount user;
    private Balance balance;

    public Account(){};
    public Account(Integer accountNumber, String pin, DetailAccount user, Balance balance){
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.user = user;
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

    public DetailAccount getUser() {
        return user;
    }

    public void setUser(DetailAccount user) {
        this.user = user;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }
}
