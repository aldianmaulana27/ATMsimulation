package com.atm.simulation.repository;

import com.atm.simulation.entity.DetailAccount;


import java.util.List;

public interface UserRepository {

    void addUser();

    List<DetailAccount> getAll();

    DetailAccount getUser(String name);

    void add(DetailAccount user);

    void remove(String name);
}
