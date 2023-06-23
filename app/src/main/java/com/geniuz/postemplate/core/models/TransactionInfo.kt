package com.geniuz.postemplate.core.models

data class TransactionInfo(
    val amount: Double,
    val cashbackAmount: Double = 0.0,
    val transactionType: TransactionType
)
