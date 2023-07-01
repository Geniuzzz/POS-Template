package com.geniuz.postemplate.core.transaction

import com.geniuz.postemplate.core.models.TransactionInfo
import kotlinx.coroutines.flow.StateFlow

interface TransactionProcessor {

    val transactionStateFlow: StateFlow<TransactionState>

    suspend fun observeCardReader()

    suspend fun startTransaction(transactionInfo: TransactionInfo)

    fun selectApplication(index: Int)
}