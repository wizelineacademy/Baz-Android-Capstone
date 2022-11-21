package com.wizeline.criptocurrency

import com.wizeline.criptocurrency.data.database.entities.toAvailableBookEntityList
import com.wizeline.criptocurrency.domain.model.AvailableBook

val availableOrderBookObject = AvailableBook(
    book = "btc_mxn",
    minimum_value = "40000",
    maximum_value = "20000000",
)

val listAvailableOrderBooks = listOf(availableOrderBookObject, availableOrderBookObject)
val listAvailableOrderBooksEntities =
    listOf(availableOrderBookObject, availableOrderBookObject).toAvailableBookEntityList()
