package com.example.wizelineandroid.repository.ticker

import com.example.wizelineandroid.data.local.dao.TickerDao
import com.example.wizelineandroid.data.local.entitys.TickerEntity
import javax.inject.Inject

class TickerRoomRepoImpl @Inject constructor(private val dataRoom: TickerDao) : TickerRoomRepo {
    override suspend fun getTicker(id: String): TickerEntity = dataRoom.getTicket(id) ?: TickerEntity("1","e","","","")
    override suspend fun insertTicket(item: TickerEntity) = dataRoom.insertTicker(item)
}
