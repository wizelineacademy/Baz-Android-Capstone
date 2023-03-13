package com.example.wizelineandroid.repository.order

import com.example.wizelineandroid.data.local.entitys.AskEntity
import com.example.wizelineandroid.data.local.entitys.BidsEntity

interface OrderRoomRepo {

    suspend fun insertAsk(item: List<AskEntity>)

    suspend fun getAsk(id: String): List<AskEntity>

    suspend fun insertBids(item: List<BidsEntity>)

    suspend fun getBids(id: String): List<BidsEntity>
}
