package com.atm.simulation.service.impl;

import com.atm.simulation.entity.Account;
import com.atm.simulation.repository.AccountRepository;
import com.atm.simulation.service.AccountService;
import com.atm.simulation.util.ValidationUtil;

import java.util.*;

public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private ValidationUtil validationUtil;

    public AccountServiceImpl(AccountRepository accountRepository, ValidationUtil validationUtil){
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
    public void addAccountFromDoc(String builder) {
        List<String> value = List.of(builder.split("\r\n"));
        List<Account> accounts = new ArrayList<>();
        Map<Integer,Account> listAcc = new HashMap<>();
        for (int i = 1; i < value.size(); i++) {
            List<String> data = List.of(value.get(i).split(";"));
            Account account = new Account(Integer.parseInt(data.get(3)), data.get(1),data.get(0),Integer.parseInt(data.get(2)));
            accounts.add(account);
        }
        for (Account acc : accounts){
            if(listAcc.containsValue(acc)){
                throw new RuntimeException("Can't add with the duplicate Account : " + acc.toString() +"\n ====== all records ======\n"+accounts+ ", please update your doc and re-run the application");
            }else if(listAcc.containsKey(acc.getAccountNumber())){
                throw new RuntimeException("Can't add with the same Account Number : " + acc.getAccountNumber() + ", please update your doc and re-run the application");
            }
            listAcc.put(acc.getAccountNumber(),acc);
        }
        accountRepository.addAccount(accounts);
    }
}
