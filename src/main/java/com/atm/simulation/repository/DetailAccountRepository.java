package com.atm.simulation.repository;

import com.atm.simulation.entity.DetailAccount;


import java.util.List;

public interface DetailAccountRepository {


    List<DetailAccount> getAll();

    DetailAccount getDetailAccount(Integer accNumb);

    void add(DetailAccount user);

    void remove(Integer accNumb);
}
