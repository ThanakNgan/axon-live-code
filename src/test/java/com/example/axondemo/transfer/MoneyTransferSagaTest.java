package com.example.axondemo.transfer;

import com.example.axondemo.coreapi.MoneyTransferRequestEvent;
import com.example.axondemo.coreapi.WithdrawMoneyCommand;
import org.axonframework.test.saga.AnnotatedSagaTestFixture;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Copyright (c) GL Finance Plc. All rights reserved. (http://www.gl-f.com/)
 * Author: Thanak Ngan (t.ngan@gl-f.com) on 8/25/2017.
 */
public class MoneyTransferSagaTest {

  private AnnotatedSagaTestFixture fixture;

  @Before
  public void setUp() throws Exception {
    fixture = new AnnotatedSagaTestFixture<>(MoneyTransfer.class);
  }

  @Test
  public void moneyTransferRequest() throws Exception {
    fixture.givenNoPriorActivity()
            .whenPublishingA(new MoneyTransferRequestEvent("tf1", "acct1", "acct2", 100))
            .expectActiveSagas(1)
            .expectDispatchedCommandsEqualTo(new WithdrawMoneyCommand("acct1", 100));
  }

}