package com.atm.simulation;

import com.atm.simulation.entity.Account;
import com.atm.simulation.entity.Balance;
import com.atm.simulation.repository.AccountRepository;
import com.atm.simulation.repository.BalanceRepository;
import com.atm.simulation.repository.impl.AccountRepositoryImpl;
import com.atm.simulation.repository.impl.BalanceRepositoryImpl;
import com.atm.simulation.service.AccountService;
import com.atm.simulation.service.BalanceService;
import com.atm.simulation.service.impl.AccountServiceImpl;
import com.atm.simulation.service.impl.BalanceServiceImpl;
import com.atm.simulation.view.TransactionView;
import com.atm.simulation.view.WelcomeView;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimulationApplication {

	public static void main(String[] args) {
		AccountRepository accountRepository = new AccountRepositoryImpl();
		accountRepository.addAccount();

		BalanceRepository balanceRepository = new BalanceRepositoryImpl();
		for (Account balance : accountRepository.getAll()){
			balanceRepository.add(balance.getBalance());
		}
		BalanceService balanceService = new BalanceServiceImpl(balanceRepository);
		TransactionView transactionView = new TransactionView(balanceService);
		transactionView.showMenu();

		AccountService accountService = new AccountServiceImpl(accountRepository);
		WelcomeView welcome = new WelcomeView(accountService);

		welcome.showMenu();
	}

}
