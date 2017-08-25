package com.example.axondemo.account;

import com.example.axondemo.coreapi.CreateAccountCommand;
import com.example.axondemo.coreapi.CreateAccountEvent;
import com.example.axondemo.coreapi.WithdrawAccountCommand;
import com.example.axondemo.coreapi.WithdrawAccountEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * Copyright (c) GL Finance Plc. All rights reserved. (http://www.gl-f.com/)
 * Author: Thanak Ngan (t.ngan@gl-f.com) on 8/25/2017.
 */
@Aggregate
@NoArgsConstructor
public class Account {

  @AggregateIdentifier
  private String accountId;
  private int balance;
  private int overdraft;

  @CommandHandler
  public Account(CreateAccountCommand command){
    apply(new CreateAccountEvent(command.getAccountId(),command.getOverdraftLimit()));
  }

  @EventHandler
  public void on(CreateAccountEvent event){
    this.accountId=event.getAccountId();
    this.overdraft=event.getOverdraftLimit();
  }

  @CommandHandler
  public void handle(WithdrawAccountCommand command) throws OverdrafLimitExceedException {
    if(balance + overdraft> command.getAmount()){
      apply(new WithdrawAccountEvent(command.getAccountId(), command.getAmount(), balance-command.getAmount()));
    }
    else {
      throw new OverdrafLimitExceedException();
    }
  }

  @EventHandler
  public void on(WithdrawAccountEvent event){
    this.balance= event.getBalance();
  }

}
