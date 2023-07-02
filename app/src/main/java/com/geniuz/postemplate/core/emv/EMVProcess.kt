package com.geniuz.postemplate.core.emv

import com.geniuz.postemplate.core.models.CardApplication
import com.geniuz.postemplate.core.models.CardInfo
import com.geniuz.postemplate.core.models.TransactionInfo

sealed class EMVProcess {
    object Init: EMVProcess()
    data class OnApplicationsReady(val applications: List<CardApplication>) : EMVProcess()
    data class OnAppSelected(val application: CardInfo) : EMVProcess()
    object OnBeforeGPO : EMVProcess()
    object OnGPO : EMVProcess()
    data class OnCardHolderInputPinRequired(val isOnlinePin: Boolean, val pinTrialsLeft: Int) : EMVProcess()
    data class OnPinEntered(val pinData: String): EMVProcess()
    object OnCtlsTapAgain : EMVProcess()
    data class OnlineProcessRequired(val transactionInfo: TransactionInfo): EMVProcess()
    object OnRemoveCard : EMVProcess()
    object OnFinish : EMVProcess()
    data class OnInfo(val message: String): EMVProcess()
    data class OnError(val error: Throwable): EMVProcess()
}
