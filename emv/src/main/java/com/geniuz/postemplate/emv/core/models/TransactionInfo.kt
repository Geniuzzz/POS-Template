package com.geniuz.postemplate.emv.core.models

data class TransactionInfo(
    val amount: Double,
    val cashbackAmount: Double = 0.0,
    val transactionType: TransactionType,
    val rrn: String,
    val stan: String,
    val terminalInfo: TerminalInfo,
    val cardInfo: CardInfo? = null
)
