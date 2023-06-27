package com.geniuz.postemplate.core.transaction

import com.geniuz.postemplate.core.Pos
import com.geniuz.postemplate.core.emv.EMVProcess
import com.geniuz.postemplate.core.models.CardSlotType
import com.geniuz.postemplate.core.models.TerminalInfo
import com.geniuz.postemplate.core.models.TransactionInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest

class TransactionProcessorImpl(
    private val pos: Pos,
    private val transactionInfo: TransactionInfo,
    private val terminalInfo: TerminalInfo
) : TransactionProcessor {
    
    private val _transactionStateFlow: MutableStateFlow<TransactionState> = MutableStateFlow(TransactionState.Init)
    override val transactionStateFlow: StateFlow<TransactionState> = _transactionStateFlow

    override suspend fun startTransaction() {
        pos.emvProcessStateFlow.collectLatest { emvProcess ->
            when (emvProcess) {
                EMVProcess.Init -> {
                    pos.readCard()
                }
                is EMVProcess.OnAppSelected -> {

                }
                is EMVProcess.OnApplicationsReady -> {
                    emvProcess.applications
                }
                EMVProcess.OnBeforeGPO -> TODO()
                EMVProcess.OnCardHolderInputPin -> TODO()
                EMVProcess.OnCtlsTapAgain -> TODO()
                is EMVProcess.OnError -> TODO()
                EMVProcess.OnFinish -> TODO()
                EMVProcess.OnGPO -> TODO()
                EMVProcess.OnRemoveCard -> TODO()
            }
        }

        pos.cardInfoResultStateFlow.collectLatest { result ->
            if (result.isSuccess) {
                when (result.getOrNull()?.cardSlotType) {
                    CardSlotType.ICC -> {

                    }
                    CardSlotType.RF -> {

                    }
                    CardSlotType.SWIPE -> {

                    }
                    null -> {

                    }
                }
            } else {

            }
        }
    }

    override fun selectApplication(index: Int) {
        pos.selectApplicationPosition(index)
    }
}