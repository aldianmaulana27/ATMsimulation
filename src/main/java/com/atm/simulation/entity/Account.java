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

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", pin='" + pin + '\'' +
                ", user=" + user.getName() +
                ", balance=" + balance.getBalance() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;
        return accountNumber.equals(account.accountNumber) && pin.equals(account.pin) && user.getName().equals(account.user.getName()) && balance.getBalance().equals(account.balance.getBalance());
    }

    @Override
    public int hashCode() {
        int result = accountNumber.hashCode();
        result = 31 * result + pin.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + balance.hashCode();
        return result;
    }
}
