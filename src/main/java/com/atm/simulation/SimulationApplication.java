package com.atm.simulation;

import com.atm.simulation.repository.AccountRepository;
import com.atm.simulation.repository.impl.AccountRepositoryImpl;
import com.atm.simulation.service.AccountService;
import com.atm.simulation.service.TransactionService;
import com.atm.simulation.service.impl.AccountServiceImpl;
import com.atm.simulation.service.impl.TransactionServiceImpl;
import com.atm.simulation.util.InputUtil;
import com.atm.simulation.util.ValidationUtil;
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
		InputUtil inputUtil = new InputUtil();
		ValidationUtil validationUtil = new ValidationUtil();
		AccountService accountService = new AccountServiceImpl(accountRepository,validationUtil);
		WithdrawView withdrawView = new WithdrawView(accountService,inputUtil);
		TransferView transferView = new TransferView(accountService, inputUtil, validationUtil);
		TransactionService transactionService = new TransactionServiceImpl(accountService,validationUtil);
		TransactionView transactionView = new TransactionView(withdrawView,transferView, transactionService, inputUtil);
		WelcomeView welcome = new WelcomeView(accountService,transactionView,inputUtil);
		welcome.showMenu();
	}

}
