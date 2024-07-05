package com.atm.simulation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "account")
public class Account implements Serializable {

    @Id
    @Column(name = "account_number")
    private Integer accountNumber;
    private String pin;
    private String name;
    private Integer balance;

    public Account(){};
    public Account(Integer accountNumber, String pin, String name, Integer balance){
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.name = name;
        this.balance = balance;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    private String getPin() {
        return pin;
    }


    public Integer getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;
        return Objects.equals(accountNumber, account.accountNumber) && Objects.equals(pin, account.pin) && Objects.equals(name, account.name) && Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(accountNumber);
        result = 31 * result + Objects.hashCode(pin);
        result = 31 * result + Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(balance);
        return result;
    }

    public boolean checkPin(String pin) {
         return pin.equalsIgnoreCase(getPin());
    }

    public void decreasBalance(Integer amount, boolean withdraw) {
        if (amount > 1000) {
            throw new RuntimeException("Maximum amount to withdraw is $1000");
        }
        if (withdraw && amount % 10 != 0) {
            throw new RuntimeException("Invalid ammount");
        }
        if (amount > this.balance) {
            throw new RuntimeException("Insufficient balance $" + balance);
        }
        this.balance -= amount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", pin='" + pin + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }

    public void fundBalance(Account account, String amount) {
        //validate
        if (Integer.parseInt(amount) > account.getBalance()) {
            throw new RuntimeException("Insufficient balance $" + amount);
        }
        if (Integer.parseInt(amount) > 1000) {
            throw new RuntimeException("Maximum amount to transfer is $1000");
        } else if (Integer.parseInt(amount) < 1) {
            throw new RuntimeException("Minimum amount to transfer is $1");
        }
            this.balance += Integer.parseInt(amount);
    }
}
