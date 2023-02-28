package com.example.wizelineandroid.repository.ticker

import com.example.wizelineandroid.data.local.dao.BookDao
import com.example.wizelineandroid.data.local.dao.TickerDao
import com.example.wizelineandroid.data.local.entitys.TickerEntity

class TickerRoomRepoImpl(private val dataRoom: TickerDao): TickerRoomRepo {
    override suspend fun getTicker(id: String): TickerEntity = dataRoom.getTicket(id)
    override suspend fun insertTicket(item: TickerEntity) = dataRoom.insertTicker(item)
}