package com.lefg095.criptoone.data.interfaces

import com.lefg095.criptoone.domain.stateevent.DataState
import com.lefg095.criptoone.domain.response.TickerResponse
import kotlinx.coroutines.flow.Flow

interface ITickerRepository {
    fun getTicker(nameBook: String): Flow<DataState<TickerResponse>>
}
