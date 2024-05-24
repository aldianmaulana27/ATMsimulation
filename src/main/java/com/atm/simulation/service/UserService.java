package com.atm.simulation.service;

import com.atm.simulation.entity.User;

import java.util.List;

public interface UserService {
    List<User> showAllUser();
    User showUser(String name);
    void addUser(User user);
}
