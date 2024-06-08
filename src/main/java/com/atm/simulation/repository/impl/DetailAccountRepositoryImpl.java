package com.atm.simulation.repository.impl;

import com.atm.simulation.entity.DetailAccount;
import com.atm.simulation.repository.DetailAccountRepository;

import java.util.ArrayList;
import java.util.List;

public class DetailAccountRepositoryImpl implements DetailAccountRepository {

    public List<DetailAccount> listDetail = new ArrayList<>();

    @Override
    public List<DetailAccount> getAll() {
        return listDetail;
    }

    @Override
    public DetailAccount getDetailAccount(Integer accNumb) {
        for (DetailAccount
                detailAccount : listDetail
        ){
            if (detailAccount.getAccountNumber().equals(accNumb)){
                return detailAccount;
            };
        }
        return null;
    }


    @Override
    public void add(DetailAccount detailAccount) {
        listDetail.add(detailAccount);
    }

    @Override
    public void remove(Integer accNumb) {

    }

}
