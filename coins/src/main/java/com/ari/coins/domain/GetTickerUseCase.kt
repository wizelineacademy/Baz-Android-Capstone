package com.ari.coins.domain

import com.ari.coins.data.models.ResultData
import com.ari.coins.data.repository.CoinsRepository
import com.ari.coins.domain.contracts.SuspendUseCase
import com.ari.coins.domain.domainModels.ResultDomain
import com.ari.coins.domain.domainModels.TickerDomain
import com.ari.coins.domain.domainModels.toDomain
import javax.inject.Inject

class GetTickerUseCase @Inject constructor(
    private val coinsRepository: CoinsRepository
): SuspendUseCase<String, TickerDomain> {
    override suspend fun invoke(book: String): ResultDomain<TickerDomain> =
        when(val result = coinsRepository.getTicker(book)) {
            is ResultData.Error -> {
                val localTicker = coinsRepository.getTickerFromDB(book)

                if (localTicker == null) {
                    ResultDomain.Error(result.message, result.code)
                } else {
                    ResultDomain.Success(localTicker.toDomain())
                }
            }
            is ResultData.Success -> {
                coinsRepository.deleteTickerFromDB(book)
                coinsRepository.insertTickerToDB(result.data)
                ResultDomain.Success(result.data.toDomain())
            }
        }
}