package com.atm.simulation.repository.impl;

import com.atm.simulation.entity.Account;
import com.atm.simulation.entity.Balance;
import com.atm.simulation.entity.User;
import com.atm.simulation.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    public List<User> listUser = new ArrayList<>();

    @Override
    public void addUser(){
        listUser.add(new User("John Doe", new Account(112233,"012108","John Doe", new Balance(100,112233))));
        listUser.add(new User("Jane Doe", new Account(112244,"932012","John Doe", new Balance(30,112244))));
    }

    @Override
    public List<User> getAll() {
        return listUser;
    }



    @Override
    public User getUser(String name) {
        for (User user : listUser){
            if (user.getName().equalsIgnoreCase(name)){
                return user;
            };
        }
        return null;
    }

    @Override
    public void add(User user) {

    }

    @Override
    public void remove(String name) {

    }
}
