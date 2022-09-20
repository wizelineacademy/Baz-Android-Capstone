package com.ari.coins.domain

import com.ari.coins.data.models.Result
import com.ari.coins.data.models.Ticker
import com.ari.coins.data.network.repository.CoinsRepository
import javax.inject.Inject

class GetTickerUseCase @Inject constructor(
    private val coinsRepository: CoinsRepository
): UseCase<String, Ticker> {
    override suspend fun invoke(book: String): Result<Ticker> {
        return coinsRepository.getTicker(book)
    }
}