package com.ari.coins.domain

import com.ari.coins.data.models.OrderBook
import com.ari.coins.data.models.Result
import com.ari.coins.data.network.repository.CoinsRepository
import com.ari.coins.domain.contracts.SuspendUseCase
import javax.inject.Inject

class GetOrderBookUseCase @Inject constructor(
    private val coinsRepository: CoinsRepository
): SuspendUseCase<String, OrderBook> {
    override suspend fun invoke(book: String): Result<OrderBook> {
        return coinsRepository.getOrderBook(book)
    }
}