package com.javg.cryptocurrencies.data.repository

open class CRYGenericRepository{
    suspend fun <T>getResponse(
        callFunction: suspend () -> T){
        try {
            callFunction.invoke()
        }catch (e: Exception){
            println("exception getResponse -> ${e.message}")
        }
    }
}