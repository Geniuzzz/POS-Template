package com.geniuz.postemplate.emv.core.emv

import com.geniuz.postemplate.emv.core.models.CardInfo

sealed class CardReaderState {
    object Searching : CardReaderState()
    data class CardFound(val cardInfo: CardInfo) : CardReaderState()
    data class OnError(val error: Throwable) : CardReaderState()
}
