package com.example.wizelineandroid.repository.ticker

import com.example.wizelineandroid.data.local.entitys.TickerEntity

interface TickerRoomRepo {
    suspend fun getTicker(id: String): TickerEntity

    suspend fun insertTicket(item: TickerEntity)

}