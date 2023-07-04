package com.geniuz.postemplate.emv.core.models

enum class TransactionType(val code: Int = 0x00) {
    PURCHASE(0x00),
    PURCHASE_WITH_CASHBACK,
    REVERSAL,
    REFUND,
    PRE_AUTHORIZATION,
    PRE_AUTHORIZATION_COMPLETION,
}
