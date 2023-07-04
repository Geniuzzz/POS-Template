package com.geniuz.postemplate.emv.core.transaction

import com.geniuz.postemplate.emv.core.models.CardApplication
import com.geniuz.postemplate.emv.core.models.TransactionInfo

sealed class TransactionState {
    object Init : TransactionState()
    data class CardApplicationSelection(val applications: List<CardApplication>) : TransactionState()
    data class CardPinRequest(val isOnlinePin: Boolean, val pinTrialsLeft: Int) : TransactionState()
    data class OnlineRequired(val transactionInfo: TransactionInfo) : TransactionState()
    data class Error(val errorMessage: String) : TransactionState()
}
