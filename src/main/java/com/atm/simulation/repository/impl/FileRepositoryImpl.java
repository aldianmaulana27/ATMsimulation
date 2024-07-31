package com.atm.simulation.repository.impl;

import com.atm.simulation.entity.Account;
import com.atm.simulation.repository.FileRepository;

import java.util.ArrayList;
import java.util.List;

public class FileRepositoryImpl implements FileRepository {
    List<Account> listDataFile = new ArrayList<>();
    @Override
    public void addDataFile(List<Account> accounts) {
        listDataFile.addAll(accounts);
    }

    @Override
    public List<Account> getAllDataFile() {
        return listDataFile;
    }
}
