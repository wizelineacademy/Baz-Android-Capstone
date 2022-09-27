package com.ari.coins.domain

import com.ari.coins.data.models.AvailableBookData
import com.ari.coins.data.models.ResultData
import com.ari.coins.data.network.repository.CoinsRepository
import com.ari.coins.domain.contracts.SuspendUseCase
import javax.inject.Inject

class GetAvailableBooksUseCase @Inject constructor(
    private val coinsRepository: CoinsRepository
): SuspendUseCase<Nothing?, List<AvailableBookData>> {
    override suspend fun invoke(params: Nothing?): ResultData<List<AvailableBookData>> {
        return coinsRepository.getAvailableBooks()
    }
}