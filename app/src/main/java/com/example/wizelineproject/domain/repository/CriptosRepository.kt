package com.example.wizelineproject.domain.repository

import android.util.Log
import com.example.wizelineproject.domain.model.Book
import com.example.wizelineproject.domain.model.OrderBook
import com.example.wizelineproject.domain.model.Ticker
import com.example.wizelineproject.domain.service.CriptomonedasServices
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import javax.inject.Inject

class CriptosRepository: GenericRepository<CriptomonedasServices>(){

    override lateinit var api: CriptomonedasServices

    fun configRepository(retrofit: Retrofit, criptomonedasServices: CriptomonedasServices){
        client = retrofit
        api = criptomonedasServices
    }

    suspend fun getBooks(callback: (success:Boolean, data: List<Book>)->Unit){
        getResponseWithArray({
            api.getBooks()
        }){ success: Boolean, data: List<Book> ->
            callback(success, data)
        }
    }

    suspend fun getTicker(book:String, callback: (success:Boolean, data: Ticker?)->Unit){
        getResponseWithObject({
            api.getTickers(book)
        }){ success: Boolean, data: Ticker? ->
            callback(success, data)
        }
    }

    suspend fun getTransactions(book:String, callback: (success: Boolean, data: OrderBook?) -> Unit){
        getResponseWithObject({
            api.getBookOrders(book)
        }){ success: Boolean, data: OrderBook? ->
            callback(success, data)
        }
    }

    suspend fun getOrderBooks(book:String, callback: (success:Boolean, data: OrderBook?)->Unit){
        getResponseWithObject({
            api.getBookOrders(book)
        }){ success: Boolean, data: OrderBook? ->
            callback(success, data)
        }
    }

}