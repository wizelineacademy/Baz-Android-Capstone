package com.carteagal.baz_android.domain.useCase

import com.carteagal.baz_android.data.model.tickerResponse.TickerResponse
import com.carteagal.baz_android.data.network.Resources
import com.carteagal.baz_android.data.network.Resources.Error
import com.carteagal.baz_android.data.network.Resources.Success
import com.carteagal.baz_android.data.repository.TickerRepository
import java.lang.Exception
import javax.inject.Inject

class GetTickerUserCase @Inject constructor(
    private val tickerRepository: TickerRepository
) {
    suspend operator fun invoke(data: String): Resources<TickerResponse>{
        return try {
            Success(tickerRepository.getTickerBook(data))
        }catch (e: Exception){
            Error(e.message)
        }
    }
}