package com.ari.coins.domain

import com.ari.coins.data.models.AvailableBook
import com.ari.coins.data.models.Result
import com.ari.coins.data.network.repository.CoinsRepository
import javax.inject.Inject

class GetAvailableBooksUseCase @Inject constructor(
    private val coinsRepository: CoinsRepository
): UseCase<Nothing?, List<AvailableBook>> {
    override suspend fun invoke(params: Nothing?): Result<List<AvailableBook>> {
        return coinsRepository.getAvailableBooks()
    }
}