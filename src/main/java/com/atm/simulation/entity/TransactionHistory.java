package com.atm.simulation.entity;


import com.atm.simulation.util.Constants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionHistory {

    private Integer accountNumber;
    private LocalDateTime date;
    private String transactionType;
    private Integer amount;
    private Integer accountDestination;
    private Integer referenceNumber;


    public TransactionHistory(Integer accountNumber, LocalDateTime date, String transactionType, Integer amount, Integer accountDestination, Integer referenceNumber) {
        this.accountNumber = accountNumber;
        this.date = date;
        this.transactionType = transactionType;
        this.amount = amount;
        this.accountDestination = accountDestination;
        this.referenceNumber = referenceNumber;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getAccountDestination() {
        return accountDestination;
    }

    public void setAccountDestination(Integer accountDestination) {
        this.accountDestination = accountDestination;
    }

    public Integer getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(Integer referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    @Override
    public String toString() {
        if(transactionType.equalsIgnoreCase(Constants.TransactionType.WITHDRAW)){
            return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a")) +"\n"+
                    transactionType+ " $"+ amount+"\n";
        }else{
            return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a"))+"\n"+
                    transactionType+ " $"+ amount +
                    " to : " + accountDestination +
                    ", refNumber :" + referenceNumber+"\n";
        }

    }
}
