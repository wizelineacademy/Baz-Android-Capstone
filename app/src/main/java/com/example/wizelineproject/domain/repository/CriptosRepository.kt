package com.example.wizelineproject.domain.repository

import com.example.wizelineproject.domain.model.Book
import com.example.wizelineproject.domain.model.OrderBook
import com.example.wizelineproject.domain.model.Ticker
import com.example.wizelineproject.domain.service.CriptomonedasServices

class CriptosRepository : GenericRepository<CriptomonedasServices>(){

    override var api: CriptomonedasServices = client.create(CriptomonedasServices::class.java)

    suspend fun getBooks(callback: (success:Boolean, data: List<Book>)->Unit){
        getResponseWithArray({
            api.getBooks()
        }){ success: Boolean, data: List<Book> ->
            callback(success, data)
        }
    }

    suspend fun getTickers(callback: (success:Boolean, data: List<Ticker>)->Unit){
        getResponseWithArray({
            api.getTickers()
        }){ success: Boolean, data: List<Ticker> ->
            callback(success, data)
        }
    }

    suspend fun getOrderBooks(callback: (success:Boolean, data: OrderBook?)->Unit){
        getResponseWithObject({
            api.getBookOrders()
        }){ success: Boolean, data: OrderBook? ->
            callback(success, data)
        }
    }

    //la documentacion de los servicios de BITSO tiene un error
    /*suspend fun getTickers(callback: (success:Boolean, data: Ticker?)->Double){
        getResponseWithObject({
            api.getTickers()
        }){ success: Boolean, data: Ticker? ->
            callback(success, data)
        }
    }*/

}