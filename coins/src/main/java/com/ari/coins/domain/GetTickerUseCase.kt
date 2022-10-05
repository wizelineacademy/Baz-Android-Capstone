package com.ari.coins.domain

import com.ari.coins.data.models.ResultData
import com.ari.coins.data.repository.CoinsRepository
import com.ari.coins.domain.contracts.SuspendUseCase
import com.ari.coins.domain.domainModels.ResultDomain
import com.ari.coins.domain.domainModels.TickerDomain
import com.ari.coins.domain.domainModels.toDomain
import javax.inject.Inject

/**
 * @author Ari Valencia
 * @file GetTickerUseCase
 * @description This UseCase returns a sealed class (TickerDomain) with
 *                  Success:
 *                   - When the server returns successful (and save in local)
 *                   - When the server returns an error but we have the info locally
 *                  Error:
 *                   - When the server returns an error and we do not have local information
 */

class GetTickerUseCase @Inject constructor(
    private val coinsRepository: CoinsRepository
) : SuspendUseCase<String, TickerDomain> {

    override suspend fun invoke(book: String): ResultDomain<TickerDomain> =
        when (val result = coinsRepository.getTicker(book)) {
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
