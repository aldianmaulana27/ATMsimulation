package com.atm.simulation;

import com.atm.simulation.repository.AccountRepository;
import com.atm.simulation.repository.impl.AccountRepositoryImpl;
import com.atm.simulation.service.AccountService;
import com.atm.simulation.service.TransactionService;
import com.atm.simulation.service.impl.AccountServiceImpl;
import com.atm.simulation.service.impl.TransactionServiceImpl;
import com.atm.simulation.view.TransactionView;
import com.atm.simulation.view.TransferView;
import com.atm.simulation.view.WelcomeView;
import com.atm.simulation.view.WithdrawView;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimulationApplication {

	public static void main(String[] args) {
		AccountRepository accountRepository = new AccountRepositoryImpl();
		accountRepository.addAccount();

		AccountService accountService = new AccountServiceImpl(accountRepository);
		WithdrawView withdrawView = new WithdrawView(accountService);
		TransferView transferView = new TransferView(accountService);
		TransactionService transactionService = new TransactionServiceImpl(accountService);
		TransactionView transactionView = new TransactionView(withdrawView,transferView, transactionService);
		WelcomeView welcome = new WelcomeView(accountService,transactionView);
		welcome.showMenu();
	}

}
