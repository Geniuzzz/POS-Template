package com.geniuz.postemplate.core.emv

sealed class EMVProcess {
    object Init: EMVProcess()
    data class OnApplicationsReady(val applications: List<String>) : EMVProcess()
    data class OnAppSelected(val application: String) : EMVProcess()
    object OnBeforeGPO : EMVProcess()
    object OnGPO : EMVProcess()
    object OnCardHolderInputPin : EMVProcess()
    object OnCtlsTapAgain : EMVProcess()
    object OnRemoveCard : EMVProcess()
    object OnFinish : EMVProcess()
    data class OnError(val message: String): EMVProcess()
}
