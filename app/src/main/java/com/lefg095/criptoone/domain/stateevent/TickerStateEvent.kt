package com.lefg095.criptoone.domain.stateevent

sealed class TickerStateEvent {
    data class GetTicker(
        val nameBook: String
    ): TickerStateEvent()

    object None : TickerStateEvent()

}
