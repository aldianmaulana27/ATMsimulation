package com.atm.simulation.service.impl;

import com.atm.simulation.entity.User;
import com.atm.simulation.repository.UserRepository;
import com.atm.simulation.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public List<User> showAllUser() {
//        for (User user : userRepository.getAll()){
//            System.out.println("Name : " + user.getName() +"\nPIN : " + user.getAccount().getPin() +
//                    "\nBalance : " + user.getBalance().getBalance() +"\nAccount Number : " + user.getAccount().getAccountNumber() + "\n");
//        }
        return userRepository.getAll();
    }

    @Override
    public User showUser(String name) {
        for (User user : userRepository.getAll()){
            if(name.equalsIgnoreCase(user.getName())){
                return user;
            }
        }
        return null;
    }

    @Override
    public void addUser(User user) {

    }
}
