package com.wizeline.criptocurrency.domain.model.use_case

import com.wizeline.criptocurrency.common.adapters.RequestState
import com.wizeline.criptocurrency.data.repository.BitsoRepository
import com.wizeline.criptocurrency.domain.model.Ticker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TickerUseCase @Inject constructor(private val repository: BitsoRepository) {

    operator fun invoke(book: String): Flow<RequestState<Ticker>> = flow {
        try {
            emit(RequestState.Loading())
            val response = repository.getTicker(book)
            if (response.body()?.success == true ){
                val ticker: Ticker = response.let {
                    it.body()?.payload?.toTicker() ?: Ticker()
                }
                emit(RequestState.Success(ticker))
            }
        } catch (e: HttpException) {
            emit(RequestState.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(RequestState.Error("Couldn't reach server."))
        }
    }

}