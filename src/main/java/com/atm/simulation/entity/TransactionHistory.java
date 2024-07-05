package com.atm.simulation.entity;

import com.atm.simulation.enums.TransactionTypes;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "transactions_history")
public class TransactionHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_number")
    private Integer accountNumber;
    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;
    @Column(name = "transaction_type")
    private TransactionTypes transactionType;
    private Integer amount;
    @Column(name = "account_destination")
    private Integer accountDestination;
    @Column(name = "reference_number")
    private Integer referenceNumber;
    
    public TransactionHistory(Integer accountNumber, LocalDateTime transactionDate, TransactionTypes transactionType, Integer amount, Integer accountDestination, Integer referenceNumber) {
        this.accountNumber = accountNumber;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.amount = amount;
        this.accountDestination = accountDestination;
        this.referenceNumber = referenceNumber;
    }
    public TransactionHistory(Integer accountNumber, LocalDateTime transactionDate, TransactionTypes transactionType, Integer amount) {
        this.accountNumber = accountNumber;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    @Override
    public String toString() {
        if(transactionType.equals(TransactionTypes.WITHDRAW)){
            return transactionDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a")) +"\n"+
                    transactionType+ " $"+ amount+"\n";
        }else{
            return transactionDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a"))+"\n"+
                    transactionType+ " $"+ amount +
                    " to " + accountDestination +
                    ", refNumber : " + referenceNumber+"\n";
        }

    }
}