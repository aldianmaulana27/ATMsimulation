package com.atm.simulation.service.impl;

import com.atm.simulation.entity.User;
import com.atm.simulation.repository.DetailAccountRepository;
import com.atm.simulation.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private DetailAccountRepository detailAccountRepository;

    public UserServiceImpl(DetailAccountRepository detailAccountRepository) {
        this.detailAccountRepository = detailAccountRepository;

    }

    @Override
    public List<User> showAllUser() {
//        for (User user : userRepository.getAll()){
//            System.out.println("Name : " + user.getName() +"\nPIN : " + user.getAccount().getPin() +
//                    "\nBalance : " + user.getBalance().getBalance() +"\nAccount Number : " + user.getAccount().getAccountNumber() + "\n");
//        }
        return detailAccountRepository.getAll();
    }

    @Override
    public User showUser(String name) {
        for (User user : detailAccountRepository.getAll()){
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
