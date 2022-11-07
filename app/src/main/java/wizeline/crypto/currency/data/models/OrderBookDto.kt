package wizeline.crypto.currency.data.models

import wizeline.crypto.currency.domain.model.OrderbookModel
import wizeline.crypto.currency.domain.model.AsksBidsModel


data class OrderBookDto(
    val success: Boolean,
    val payload: PayLoadOrderBook
)

fun OrderBookDto.toListOrderBook(): OrderbookModel{
    val askList = payload.asks?.map {
        AsksBidsModel(
            amount = it.amount,
            book = it.book,
            price = it.price
        )
    }?.toList()

    val bidList = payload.bids?.map {
        AsksBidsModel(
            amount = it.amount,
            book = it.book,
            price = it.price
        )
    }?.toList()

    return OrderbookModel(
        asks = askList ?: emptyList(),
        bids = bidList ?: emptyList()
    )
}