package com.geniuz.postemplate.emv.core.models

data class TerminalInfo(
    var terminalId: String,
    var merchantId: String,
    var merchantNameAndLocation: String,
    var mcc: String,
    var currencyCode: String,
    var countryCode: String,
    var panEntryMode: String,
    var pinEntryCapability: String,
    var posPinCaptureCode: String,
    var posConditionCode: String
) {
    val posEntryMode
        get() = panEntryMode + pinEntryCapability
}
