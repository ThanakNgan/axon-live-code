package com.example.axondemo.coreapi

import org.axonframework.commandhandling.TargetAggregateIdentifier

/**
 * Copyright (c) GL Finance Plc. All rights reserved. (http://www.gl-f.com/)
 * Author: Thanak Ngan (t.ngan@gl-f.com) on 8/25/2017.
 */
class CreateAccountCommand(val accountId:String, val overdraftLimit:Int)
class WithdrawAccountCommand(@TargetAggregateIdentifier val accountId: String, val amount:Int)

class CreateAccountEvent(val accountId: String, val overdraftLimit: Int)
class WithdrawAccountEvent(val accountId: String, val amount: Int, val balance:Int)
