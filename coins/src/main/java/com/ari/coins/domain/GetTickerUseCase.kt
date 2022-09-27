package com.ari.coins.domain

import com.ari.coins.data.models.ResultData
import com.ari.coins.data.models.TickerData
import com.ari.coins.data.network.repository.CoinsRepository
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
            is ResultData.Error -> ResultDomain.Error(result.message, result.code)
            is ResultData.Success -> ResultDomain.Success(result.data.toDomain())
        }
}