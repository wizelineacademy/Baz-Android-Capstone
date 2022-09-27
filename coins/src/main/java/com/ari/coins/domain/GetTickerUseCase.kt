package com.ari.coins.domain

import com.ari.coins.data.models.ResultData
import com.ari.coins.data.models.TickerData
import com.ari.coins.data.network.repository.CoinsRepository
import com.ari.coins.domain.contracts.SuspendUseCase
import javax.inject.Inject

class GetTickerUseCase @Inject constructor(
    private val coinsRepository: CoinsRepository
): SuspendUseCase<String, TickerData> {
    override suspend fun invoke(book: String): ResultData<TickerData> {
        return coinsRepository.getTicker(book)
    }
}