package com.atm.simulation;

import com.atm.simulation.repository.AccountRepository;
import com.atm.simulation.repository.TransactionHistoryRepository;
import com.atm.simulation.repository.impl.AccountRepositoryImpl;
import com.atm.simulation.repository.impl.TransactionHistoryRepositoryImpl;
import com.atm.simulation.service.AccountService;
import com.atm.simulation.service.TransactionService;
import com.atm.simulation.service.impl.AccountServiceImpl;
import com.atm.simulation.service.impl.TransactionServiceImpl;
import com.atm.simulation.util.InputUtil;
import com.atm.simulation.util.ValidationUtil;
import com.atm.simulation.view.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimulationApplication {

	public static void main(String[] args) {
//		"doc/DataAccount.csv";
		AccountRepository accountRepository = new AccountRepositoryImpl();
		InputUtil inputUtil = new InputUtil();
		ValidationUtil validationUtil = new ValidationUtil();
		AccountService accountService = new AccountServiceImpl(accountRepository,validationUtil);
		WithdrawView withdrawView = new WithdrawView(accountService,inputUtil, validationUtil);
		TransferView transferView = new TransferView(accountService, inputUtil, validationUtil);
		TransactionHistoryRepository transactionHistoryRepository = new TransactionHistoryRepositoryImpl();
		TransactionService transactionService = new TransactionServiceImpl(accountService,validationUtil,transactionHistoryRepository);
		TransactionHistoryView transactionHistoryView = new TransactionHistoryView(transactionService);
		TransactionView transactionView = new TransactionView(withdrawView,transferView,transactionHistoryView, transactionService, inputUtil);
		WelcomeView welcome = new WelcomeView(accountService,transactionView,inputUtil);
		welcome.uploadFIle("doc/DataAccount.csv");
		welcome.showMenu();
	}

}
