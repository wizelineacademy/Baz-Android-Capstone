package com.ari.coins.domain

import com.ari.coins.data.models.AvailableBooks
import com.ari.coins.data.models.OrderBook
import com.ari.coins.data.models.Result
import com.ari.coins.data.network.repository.CoinsRepository
import javax.inject.Inject

class GetAvailableBooksUseCase @Inject constructor(
    private val coinsRepository: CoinsRepository
): UseCase<Nothing?, List<AvailableBooks>> {
    override suspend fun invoke(params: Nothing?): Result<List<AvailableBooks>> {
        return coinsRepository.getAvailableBooks()
    }
}