package com.geniuz.postemplate.core

import com.geniuz.postemplate.core.models.TerminalInfo
import com.geniuz.postemplate.core.models.TransactionInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

class TransactionProcessor(
    private val pos: Pos,
    private val transactionInfo: TransactionInfo,
    private val terminalInfo: TerminalInfo
) {

    val floe = MutableStateFlow("")

    val emvProcessLiveData = pos.emvProcessLiveData

    val cardReaderResultLiveData = pos.cardInfoResultLiveData

    suspend fun startTransaction() {
        floe.collect{

        }
    }
}