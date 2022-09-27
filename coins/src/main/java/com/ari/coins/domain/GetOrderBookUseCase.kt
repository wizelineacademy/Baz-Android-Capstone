package com.ari.coins.domain

import com.ari.coins.data.models.OrderBookData
import com.ari.coins.data.models.ResultData
import com.ari.coins.data.network.repository.CoinsRepository
import com.ari.coins.domain.contracts.SuspendUseCase
import javax.inject.Inject

class GetOrderBookUseCase @Inject constructor(
    private val coinsRepository: CoinsRepository
): SuspendUseCase<String, OrderBookData> {
    override suspend fun invoke(book: String): ResultData<OrderBookData> {
        return coinsRepository.getOrderBook(book)
    }
}