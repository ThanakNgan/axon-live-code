package com.example.axondemo;

import com.example.axondemo.coreapi.CreateAccountCommand;
import com.example.axondemo.coreapi.WithdrawAccountCommand;
import org.axonframework.commandhandling.AsynchronousCommandBus;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.axonframework.spring.config.EnableAxonAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.axonframework.commandhandling.GenericCommandMessage.asCommandMessage;

@EnableAxonAutoConfiguration
@SpringBootApplication
public class AxonDemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext config = SpringApplication.run(AxonDemoApplication.class, args);

		CommandBus commandBus = config.getBean(CommandBus.class);

		commandBus.dispatch(asCommandMessage(new CreateAccountCommand("4321", 500)));
		commandBus.dispatch(asCommandMessage(new WithdrawAccountCommand("4321", 250)));
		commandBus.dispatch(asCommandMessage(new WithdrawAccountCommand("4321", 251)));
	}

	@Bean
	public EventStorageEngine engine(){
		return new InMemoryEventStorageEngine();
	}

	@Bean
	public CommandBus commandBus(){
		return new AsynchronousCommandBus();
	}
}
