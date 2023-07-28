package com.geniuz.postemplate.emv.core.models

data class TransactionInfo(
    var amount: Double,
    var cashbackAmount: Double = 0.0,
    var transactionType: TransactionType,
    var rrn: String,
    var stan: String,
    var terminalInfo: TerminalInfo,
    var cardInfo: CardInfo? = null
)
