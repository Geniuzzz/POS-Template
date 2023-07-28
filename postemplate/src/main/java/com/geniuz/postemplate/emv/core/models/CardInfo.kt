package com.geniuz.postemplate.emv.core.models

data class CardInfo(
    var pan: String,
    var expiry: String,
    var track2: String,
    var cardHolderName: String,
    var sequenceNumber: String,
    var iccData: String,
    var cardSlotType: CardSlotType?,
    var pinData: String? = null,
)
