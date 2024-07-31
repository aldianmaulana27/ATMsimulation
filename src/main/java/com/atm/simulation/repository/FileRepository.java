package com.atm.simulation.repository;

import com.atm.simulation.entity.Account;

import java.util.List;

public interface FileRepository {
    void addDataFile(List<Account> accounts);
    List<Account> getAllDataFile();
}
