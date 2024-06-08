package com.atm.simulation.entity;

public class DetailAccount {
    private String name;
    private Integer accountNumber;


    public DetailAccount(){};
    public DetailAccount(String name,
                         Integer accountNumber){
        this.name = name;
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

}
