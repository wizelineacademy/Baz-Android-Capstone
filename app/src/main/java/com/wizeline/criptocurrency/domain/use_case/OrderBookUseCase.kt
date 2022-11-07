package com.wizeline.criptocurrency.domain.model.use_case

import com.wizeline.criptocurrency.common.adapters.RequestState
import com.wizeline.criptocurrency.domain.repository.BitsoRepository
import com.wizeline.criptocurrency.domain.model.OrderBook
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class OrderBookUseCase @Inject constructor(private val repository: BitsoRepository) {

    operator fun invoke(book: String): Flow<RequestState<OrderBook>> = flow {
        try {
            emit(RequestState.Loading())
            val response = repository.getOrderBook(book)
                emit(RequestState.Success(response))
            }
         catch (e: HttpException) {
            emit(RequestState.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(RequestState.Error("Couldn't reach server."))
        }
    }

}