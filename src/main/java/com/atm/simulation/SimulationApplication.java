package com.atm.simulation;

import com.atm.simulation.repository.AccountRepository;
import com.atm.simulation.repository.TransactionHistoryRepository;
import com.atm.simulation.repository.impl.AccountRepositoryImpl;
import com.atm.simulation.repository.impl.TransactionHistoryRepositoryImpl;
import com.atm.simulation.service.AccountService;
import com.atm.simulation.service.AuthenticationService;
import com.atm.simulation.service.TransactionService;
import com.atm.simulation.service.impl.AccountServiceImpl;
import com.atm.simulation.service.impl.AuthenticationServiceImpl;
import com.atm.simulation.service.impl.TransactionServiceImpl;
import com.atm.simulation.util.InputUtil;
import com.atm.simulation.util.ValidationUtil;
import com.atm.simulation.view.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimulationApplication {

	public static void main(String[] args) {
		AccountRepository accountRepository = new AccountRepositoryImpl();
		TransactionHistoryRepository transactionHistoryRepository = new TransactionHistoryRepositoryImpl();
//		accountRepository.addAccount();
		InputUtil inputUtil = new InputUtil();
		ValidationUtil validationUtil = new ValidationUtil();
		AccountService accountService = new AccountServiceImpl(accountRepository,validationUtil);
		WithdrawView withdrawView = new WithdrawView(inputUtil,validationUtil);
		TransferView transferView = new TransferView(inputUtil, validationUtil);
		AuthenticationService authenticationService = new AuthenticationServiceImpl(accountRepository,validationUtil);
		TransactionService transactionService = new TransactionServiceImpl(transactionHistoryRepository,accountService,validationUtil);
		TransactionHistoryView transactionHistoryView = new TransactionHistoryView(transactionService);
		TransactionView transactionView = new TransactionView(withdrawView,transferView,transactionHistoryView,transactionService, inputUtil);
		WelcomeView welcome = new WelcomeView(accountService,authenticationService,transactionView,inputUtil);
		welcome.uploadFIle("doc/DataAccount.csv");
		welcome.showMenu();
	}

}
