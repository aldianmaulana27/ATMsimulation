package com.atm.simulation.entity;

public class User {
    private String name;
    private Account account;


    public User(){};
    public User(String name,
                Account account){
        this.name = name;
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
