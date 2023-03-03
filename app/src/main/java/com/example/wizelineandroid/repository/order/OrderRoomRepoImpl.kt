package com.example.wizelineandroid.repository.order

import com.example.wizelineandroid.data.local.dao.OrderDao
import com.example.wizelineandroid.data.local.entitys.AskEntity
import com.example.wizelineandroid.data.local.entitys.BidsEntity

class OrderRoomRepoImpl(private val dataRoom: OrderDao) : OrderRoomRepo {
    override suspend fun insertAsk(item: List<AskEntity>) = dataRoom.insertAsk(item)

    override suspend fun getAsk(id: String): List<AskEntity> = dataRoom.getAsk(id)

    override suspend fun insertBids(item: List<BidsEntity>) = dataRoom.insertBids(item)

    override suspend fun getBids(id: String): List<BidsEntity> = dataRoom.getBids(id)
}
