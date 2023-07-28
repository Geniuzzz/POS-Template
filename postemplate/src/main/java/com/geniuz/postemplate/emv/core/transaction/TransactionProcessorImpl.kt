package com.geniuz.postemplate.emv.core.transaction

import android.util.Log
import com.geniuz.postemplate.emv.core.data.TEST_AIDS
import com.geniuz.postemplate.emv.core.Pos
import com.geniuz.postemplate.emv.core.emv.CardReaderState
import com.geniuz.postemplate.emv.core.emv.EMVProcess
import com.geniuz.postemplate.emv.core.models.AID
import com.geniuz.postemplate.emv.core.models.CAPK
import com.geniuz.postemplate.emv.core.models.CardSlotType
import com.geniuz.postemplate.emv.core.models.TransactionInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest

abstract class TransactionProcessorImpl(
    private val pos: Pos,
) : TransactionProcessor {

    private val _transactionStateFlow: MutableStateFlow<TransactionState> =
        MutableStateFlow(TransactionState.Init)
    override val transactionStateFlow: StateFlow<TransactionState> = _transactionStateFlow

    override fun getAIDs(): List<AID> {
        return TEST_AIDS
    }

    override fun getCAPKs(): List<CAPK> {
        return emptyList()
    }

    override suspend fun observeCardReader(cardSlotTypes: List<CardSlotType>) {
        pos.cardReaderStateFlow.collectLatest { state ->
            Log.d("XXXXX CARD INFO", "TP $state")
            when (state) {
                CardReaderState.Searching -> {
                    val isInitialized = pos.initialize()
                    if (isInitialized){
                        loadDeviceAndReadCard(cardSlotTypes)
                    }else{
                        _transactionStateFlow.value =
                            TransactionState.Error("Failed to Initialize Device")
                    }
                }
                is CardReaderState.CardFound -> {
                    _transactionStateFlow.value = TransactionState.CardReady(state.cardInfo)
                }

                is CardReaderState.OnError -> {
                    _transactionStateFlow.value =
                        TransactionState.Error(state.error.message ?: "Failed to read card")
                }
            }
        }
    }

    override suspend fun startTransaction(transactionInfo: TransactionInfo) {
        pos.emvProcessStateFlow.collectLatest { emvProcess ->
            Log.d("XXXXX EMVPROCESS", "TP $emvProcess")
            when (emvProcess) {
                EMVProcess.Init -> {
                    pos.startEmvProcess(transactionInfo)
                }
                is EMVProcess.OnAppSelected -> {

                }
                is EMVProcess.OnApplicationsReady -> {
                    _transactionStateFlow.value =
                        TransactionState.CardApplicationSelection(emvProcess.applications)
                }
                EMVProcess.OnBeforeGPO -> {

                }
                is EMVProcess.OnCardHolderInputPinRequired -> {
                    _transactionStateFlow.value = TransactionState.CardPinRequest(
                        emvProcess.isOnlinePin,
                        emvProcess.pinTrialsLeft
                    )
                }
                is EMVProcess.OnPinEntered -> {
                    transactionInfo.cardInfo?.pinData = emvProcess.pinData
                }
                is EMVProcess.OnlineProcessRequired -> {
                    _transactionStateFlow.value =
                        TransactionState.OnlineRequired(transactionInfo)
                }
                EMVProcess.OnCtlsTapAgain -> TODO()
                is EMVProcess.OnError -> TODO()
                EMVProcess.OnFinish -> TODO()
                EMVProcess.OnGPO -> TODO()
                EMVProcess.OnRemoveCard -> TODO()
                is EMVProcess.OnInfo -> {

                }
            }
        }
    }

    private suspend fun loadDeviceAndReadCard(cardSlotTypes: List<CardSlotType>){
        if (getAIDs().isNotEmpty()) {
            val isAidLoaded = pos.loadAIDs(getAIDs())
            if (isAidLoaded.not()) {
                _transactionStateFlow.value =
                    TransactionState.Error("Failed to load AIDs")
                return
            }
        }

        if (getCAPKs().isNotEmpty()) {
            val isCAPKLoaded = pos.loadCAPKs(getCAPKs())
            if (isCAPKLoaded.not()) {
                _transactionStateFlow.value =
                    TransactionState.Error("Failed to load CAPKs")
                return
            }
        }

        pos.readCard(cardSlotTypes)
    }

    override fun selectApplication(index: Int) {
        pos.selectApplicationPosition(index)
    }

    override fun getICCData(): String {
        return pos.getIccData()
    }
}