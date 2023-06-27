package com.geniuz.postemplate.core.transaction

import kotlinx.coroutines.flow.StateFlow

interface TransactionProcessor {

    val transactionStateFlow: StateFlow<TransactionState>

    suspend fun startTransaction()

    fun selectApplication(index: Int)
}