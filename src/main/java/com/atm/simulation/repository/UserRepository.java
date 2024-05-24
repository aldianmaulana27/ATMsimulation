package com.atm.simulation.repository;

import com.atm.simulation.entity.User;

import java.util.List;

public interface UserRepository {

    void addUser();

    List<User> getAll();

    User getUser(String name);

    void add(User user);

    void remove(String name);
}
