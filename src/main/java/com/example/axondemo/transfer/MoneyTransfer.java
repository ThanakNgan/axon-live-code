package com.example.axondemo.transfer;

import com.example.axondemo.coreapi.*;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;
import static org.axonframework.commandhandling.model.AggregateLifecycle.markDeleted;

/**
 * Copyright (c) GL Finance Plc. All rights reserved. (http://www.gl-f.com/)
 * Author: Thanak Ngan (t.ngan@gl-f.com) on 8/25/2017.
 */
@NoArgsConstructor
@Aggregate
public class MoneyTransfer {

  @AggregateIdentifier
  private String transferId;

  @CommandHandler
  public MoneyTransfer(RequestMoneyTransferCommand command){
    apply(new MoneyTransferRequestEvent(command.getTransferId(),command.getSourceAccount(), command.getTargetAccount(),
            command.getAmount()));
  }

  @CommandHandler
  public void handle(CompleteMoneyTransferCommand command){
      apply(new MoneyTransferCompleteEvent(transferId));
  }

  @CommandHandler
  public void handle(CancelMoneyTransferCommand command){
    apply(new MoneyTransferCancelledEvent(transferId));
  }

  @EventSourcingHandler
  protected void on(MoneyTransferRequestEvent event){
    this.transferId = event.getTransferId();
  }

  @EventSourcingHandler
  protected void on(MoneyTransferCompleteEvent event){
    markDeleted();
  }

}
