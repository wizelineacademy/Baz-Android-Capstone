package com.ari.coins.domain

import com.ari.coins.data.models.ResultData
import com.ari.coins.data.network.repository.CoinsRepository
import com.ari.coins.domain.contracts.SuspendUseCase
import com.ari.coins.domain.domainModels.OrderBookDomain
import com.ari.coins.domain.domainModels.ResultDomain
import com.ari.coins.domain.domainModels.toDomain
import javax.inject.Inject

class GetOrderBookUseCase @Inject constructor(
    private val coinsRepository: CoinsRepository
): SuspendUseCase<String, OrderBookDomain> {
    override suspend fun invoke(book: String): ResultDomain<OrderBookDomain> =
        when(val result = coinsRepository.getOrderBook(book)){
            is ResultData.Error -> ResultDomain.Error(result.message, result.code)
            is ResultData.Success -> ResultDomain.Success(result.data.toDomain())
        }
}