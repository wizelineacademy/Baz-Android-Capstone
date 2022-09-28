package com.ari.coins.domain

import com.ari.coins.data.models.AvailableBookData
import com.ari.coins.data.models.ResultData
import com.ari.coins.data.repository.CoinsRepository
import com.ari.coins.domain.contracts.SuspendUseCase
import com.ari.coins.domain.domainModels.AvailableBookDomain
import com.ari.coins.domain.domainModels.ResultDomain
import com.ari.coins.domain.domainModels.toDomain
import javax.inject.Inject

class GetAvailableBooksUseCase @Inject constructor(
    private val coinsRepository: CoinsRepository
) : SuspendUseCase<Nothing?, List<AvailableBookDomain>> {
    override suspend fun invoke(params: Nothing?): ResultDomain<List<AvailableBookDomain>> =
        when (val result: ResultData<List<AvailableBookData>> = coinsRepository.getAvailableBooks()) {
            is ResultData.Error -> {
                val localAvailableBooks = coinsRepository.getAvailableBooksFromDB()

                if (localAvailableBooks.isEmpty()) {
                    ResultDomain.Error(result.message, result.code)
                } else {
                    ResultDomain.Success(localAvailableBooks.map(AvailableBookData::toDomain))
                }
            }
            is ResultData.Success -> {
                coinsRepository.clearAvailableBookTableFormDB()
                coinsRepository.insertAvailableBooksToDB(result.data)
                ResultDomain.Success(result.data.map { it.toDomain() })
            }
        }

}