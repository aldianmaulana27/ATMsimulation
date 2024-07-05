package com.atm.simulation.entity;

import com.atm.simulation.enums.TransactionTypes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionHistory {

    private Integer accountNumber;
    private LocalDateTime date;
    private TransactionTypes transactionType;
    private Integer amount;
    private Integer accountDestination;
    private Integer referenceNumber;


    public TransactionHistory(Integer accountNumber, LocalDateTime date, TransactionTypes transactionType, Integer amount, Integer accountDestination, Integer referenceNumber) {
        this.accountNumber = accountNumber;
        this.date = date;
        this.transactionType = transactionType;
        this.amount = amount;
        this.accountDestination = accountDestination;
        this.referenceNumber = referenceNumber;
    }
    public TransactionHistory(Integer accountNumber, LocalDateTime date, TransactionTypes transactionType, Integer amount) {
        this.accountNumber = accountNumber;
        this.date = date;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        if(transactionType.equals(TransactionTypes.WITHDRAW)){
            return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a")) +"\n"+
                    transactionType+ " $"+ amount+"\n";
        }else{
            return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a"))+"\n"+
                    transactionType+ " $"+ amount +
                    " to " + accountDestination +
                    ", refNumber : " + referenceNumber+"\n";
        }

    }
}