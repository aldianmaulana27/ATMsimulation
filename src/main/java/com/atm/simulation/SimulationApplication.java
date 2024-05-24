package com.atm.simulation;

import com.atm.simulation.repository.UserRepository;
import com.atm.simulation.repository.impl.UserRepositoryImpl;
import com.atm.simulation.service.UserService;
import com.atm.simulation.service.impl.UserServiceImpl;
import com.atm.simulation.view.UserView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimulationApplication {

	public static void main(String[] args) {

		UserRepository userRepository = new UserRepositoryImpl();
		userRepository.addUser();
		UserService userService = new UserServiceImpl(userRepository);
		UserView userView = new UserView(userService);

		userView.showUser();

	}

}
