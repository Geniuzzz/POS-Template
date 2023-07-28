package com.geniuz.postemplate.emv.core.transaction

import com.geniuz.postemplate.emv.core.models.AID
import com.geniuz.postemplate.emv.core.models.CAPK
import com.geniuz.postemplate.emv.core.models.CardSlotType
import com.geniuz.postemplate.emv.core.models.TransactionInfo
import kotlinx.coroutines.flow.StateFlow

interface TransactionProcessor {

    val transactionStateFlow: StateFlow<TransactionState>

    fun getAIDs(): List<AID>

    fun getCAPKs(): List<CAPK>

    suspend fun observeCardReader(cardSlotTypes: List<CardSlotType>)

    suspend fun startTransaction(transactionInfo: TransactionInfo)

    fun selectApplication(index: Int)

    fun getICCData(): String
}