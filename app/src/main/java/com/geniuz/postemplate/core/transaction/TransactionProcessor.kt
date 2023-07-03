package com.geniuz.postemplate.core.transaction

import com.geniuz.postemplate.core.models.AID
import com.geniuz.postemplate.core.models.CAPK
import com.geniuz.postemplate.core.models.TransactionInfo
import kotlinx.coroutines.flow.StateFlow

interface TransactionProcessor {

    val transactionStateFlow: StateFlow<TransactionState>

    fun getAIDs(): List<AID>

    fun getCAPKs(): List<CAPK>

    suspend fun observeCardReader()

    suspend fun startTransaction(transactionInfo: TransactionInfo)

    fun selectApplication(index: Int)
}