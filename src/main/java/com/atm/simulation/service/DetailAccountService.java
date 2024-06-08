package com.atm.simulation.service;

import com.atm.simulation.entity.DetailAccount;

import java.util.List;

public interface DetailAccountService {
    List<DetailAccount> showAllUser();
    DetailAccount showUser(Integer accNumb);
    void addUser(DetailAccount user);
}
