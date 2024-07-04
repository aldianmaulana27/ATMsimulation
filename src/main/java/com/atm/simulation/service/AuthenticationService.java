package com.atm.simulation.service;

import com.atm.simulation.entity.Account;

import java.util.Optional;

public interface AuthenticationService {
    Optional<Account> login(String accNo, String pin);
}
