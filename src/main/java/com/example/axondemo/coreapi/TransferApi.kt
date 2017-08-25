package com.example.axondemo.coreapi

import org.axonframework.commandhandling.TargetAggregateIdentifier

/**
 * Copyright (c) GL Finance Plc. All rights reserved. (http://www.gl-f.com/)
 * Author: Thanak Ngan (t.ngan@gl-f.com) on 8/25/2017.
 */
class RequestMoneyTransferCommand(@TargetAggregateIdentifier val transferId:String, val sourceAccount:String,
                                  val targetAccount:String, val amount:Int)
class MoneyTransferRequestEvent(val transferId: String, val sourceAccount: String, val targetAccount: String,
                                val amount: Int)
class CompleteMoneyTransferCommand(@TargetAggregateIdentifier val transferId: String)

class CancelMoneyTransferCommand(@TargetAggregateIdentifier val transferId: String)

class MoneyTransferCompleteEvent(val transferId: String)

class MoneyTransferCancelledEvent(val transferId: String)

class WithdrawMoneyCommand(val sourceAccount: String, val amount: Int)