package com.atm.simulation.service.impl;

import com.atm.simulation.entity.Account;
import com.atm.simulation.entity.Balance;
import com.atm.simulation.entity.DetailAccount;
import com.atm.simulation.repository.AccountRepository;
import com.atm.simulation.service.AccountService;
import com.atm.simulation.util.ValidationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private ValidationUtil validationUtil;

    public AccountServiceImpl(AccountRepository accountRepository,ValidationUtil validationUtil){
        this.accountRepository = accountRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public List<Account> showAllAccount() {
        return accountRepository.getAll();
    }

    @Override
    public Optional<Account> login(String accNo, String pin) {
        boolean check = validationUtil.checkLength(accNo, pin);
        boolean check2 = validationUtil.isNumber(accNo, pin);

        if (check2 && check){
            return accountRepository.getAccount(Integer.parseInt(accNo),pin);
        }
        return Optional.empty();
    }

    @Override
    public Account getAccount(Integer accountNumber) {
        Optional<Account> account = accountRepository.getAccount(accountNumber);
        return account.orElse(null);
    }

    @Override
    public void addAccount(Account account) {
        accountRepository.addAccount(account);
    }

    @Override
    public void addAccounts(List<Account> accounts) {
        for (Account account : accounts) {
            accountRepository.addAccount(account);
        }
    }

    @Override
    public void updateAccountBalance(Integer accNo, Integer balance) {
                accountRepository.updateAccountBalance(accNo,balance);
    }

    @Override
    public void addAccountFromDoc(String builder) {
        List<String> value = List.of(builder.split("\r\n"));
        List<Account> accounts = new ArrayList<>();
        boolean sameAccountNo ;
        for (int i = 1; i < value.size(); i++) {
            List<String> data = List.of(value.get(i).split(";"));
            Account account = new Account(Integer.parseInt(data.get(3)), data.get(1),new DetailAccount(data.get(0), Integer.parseInt(data.get(3))), new Balance(Integer.parseInt(data.get(2)),Integer.parseInt(data.get(3))));
            if(!accounts.isEmpty()){
                sameAccountNo = validationUtil.validateSameAccountNumb(accounts,Integer.parseInt(data.get(3)));
                if (!sameAccountNo) {
                    accounts.add(account);
                } else {
                    throw new RuntimeException("Can't add with the same Account Number : " + data.get(3) + ", please update your doc and re-run the application");
                }
            }else{
                accounts.add(account);
            }

        }
        addAccounts(accounts);
    }

}
