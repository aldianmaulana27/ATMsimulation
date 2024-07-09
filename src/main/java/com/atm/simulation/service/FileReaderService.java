package com.atm.simulation.service;

import com.atm.simulation.entity.Account;

import java.util.List;

public interface FileReaderService {
    List<Account> uploadDoc (String path);

}
