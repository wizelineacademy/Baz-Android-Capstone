package com.lefg095.criptoone.domain.stateevent



sealed class OrderStateEvent {
    data class GetOrder(
        val nameBook: String
    ): OrderStateEvent()

    object None : OrderStateEvent()
}
