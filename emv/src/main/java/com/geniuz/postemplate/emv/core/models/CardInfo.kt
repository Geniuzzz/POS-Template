package com.geniuz.postemplate.emv.core.models

data class CardInfo(
    val pan: String,
    val expiry: String,
    val track2: String,
    val cardHolderName: String,
    val sequenceNumber: String,
    val iccData: String,
    val cardSlotType: CardSlotType?
)
