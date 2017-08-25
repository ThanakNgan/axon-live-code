package com.example.axondemo.coreapi;

import com.example.axondemo.account.Account;
import org.axonframework.commandhandling.AsynchronousCommandBus;
import org.axonframework.config.Configuration;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;

import static org.axonframework.commandhandling.GenericCommandMessage.asCommandMessage;

/**
 * Copyright (c) GL Finance Plc. All rights reserved. (http://www.gl-f.com/)
 * Author: Thanak Ngan (t.ngan@gl-f.com) on 8/25/2017.
 */
public class Application {

  public static void main(String[] args){
    Configuration configuration= DefaultConfigurer.defaultConfiguration()
            .configureAggregate(Account.class)
            .configureEmbeddedEventStore(e-> new InMemoryEventStorageEngine())
            .configureCommandBus(e-> new AsynchronousCommandBus())
            .buildConfiguration();

    configuration.start();
    configuration.commandBus().dispatch(asCommandMessage(new CreateAccountCommand("4321", 500)));
    configuration.commandBus().dispatch(asCommandMessage(new WithdrawAccountCommand("4321", 250)));
    configuration.commandBus().dispatch(asCommandMessage(new WithdrawAccountCommand("4321", 200)));
  }
}
