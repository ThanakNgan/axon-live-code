package com.example.axondemo.account;

import com.example.axondemo.coreapi.CreateAccountCommand;
import com.example.axondemo.coreapi.CreateAccountEvent;
import com.example.axondemo.coreapi.WithdrawAccountCommand;
import com.example.axondemo.coreapi.WithdrawAccountEvent;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Copyright (c) GL Finance Plc. All rights reserved. (http://www.gl-f.com/)
 * Author: Thanak Ngan (t.ngan@gl-f.com) on 8/25/2017.
 */
public class AccountTest {
  private FixtureConfiguration<Account> fixture;
  @Before
  public void setUp() throws Exception {
    fixture = Fixtures.newGivenWhenThenFixture(Account.class);
  }
  @Test
  public void testCreateAccount(){
    fixture.givenNoPriorActivity()
            .when(new CreateAccountCommand( "1234", 1000))
            .expectEvents(new CreateAccountEvent("1234",1000));
  }

  @Test
  public void testWithdrawResonableAmount(){
    fixture.given(new CreateAccountEvent("1234",1000))
            .when(new WithdrawAccountCommand("1234", 600))
            .expectEvents(new WithdrawAccountEvent("1234", 600, -600));
  }

  @Test
  public void testWithdrawAbsurbAmount(){
    fixture.given(new CreateAccountEvent("1234",1000))
            .when(new WithdrawAccountCommand("1234", 1001))
            .expectNoEvents()
            .expectException(OverdrafLimitExceedException.class);
  }

  @Test
  public void testWithdrawTwice(){
    fixture.given(new CreateAccountEvent("1234", 1000),
            new WithdrawAccountEvent("1234",999,-999))
            .when(new WithdrawAccountCommand("1234",2))
            .expectNoEvents()
            .expectException(OverdrafLimitExceedException.class);
  }

}