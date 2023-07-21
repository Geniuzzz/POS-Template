package com.geniuz.postemplate.emv.core.models

data class TerminalInfo(
    val terminalId: String,
    val merchantId: String,
    val currencyCode: String,
    val countryCode: String,
    val panEntryMode: String,
    val pinEntryCapability: String,
    val posPinCaptureCode: String,
    val posConditionCode: String
) {
    val posEntryMode
        get() = panEntryMode + pinEntryCapability
}
