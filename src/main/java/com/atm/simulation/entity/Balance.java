package com.atm.simulation.entity;

public class Balance {

    private Integer balance;
    private Integer accountNumber;

    public Balance(){};


    public Balance(Integer balance, Integer accountNumber){
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance){
        this.balance = balance;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }
}
