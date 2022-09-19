package com.ari.coins.framework.data.network.dataSources

import com.ari.coins.data.models.AvailableBooks
import com.ari.coins.data.models.OrderBook
import com.ari.coins.data.models.Result
import com.ari.coins.data.models.Ticker
import com.ari.coins.data.network.dataSource.CoinsRemoteDataSource
import com.ari.coins.framework.data.network.apis.CoinsApi
import javax.inject.Inject

class CoinsRemoteDataSourceImpl @Inject constructor(
    private val coinsApi: CoinsApi
): CoinsRemoteDataSource {

    override suspend fun getAvailableBooks(): Result<List<AvailableBooks>> = try {
        val response = coinsApi.getAvailableBooks()

        if (response.isSuccessful) {
            val body = response.body()!!
            if (body.success) {
                Result.Success(body.payload!!)
            } else {
                Result.Error(body.error!!.message, body.error.code)
            }
        } else {
            Result.Error(response.message(), -2)
        }
    } catch (e: Exception) {
        Result.Error(e.toString(), -1)
    }

    override suspend fun getTicker(book: String): Result<Ticker> = try {
        val response = coinsApi.getTicker()

        if (response.isSuccessful) {
            val body = response.body()!!
            if (body.success) {
                Result.Success(body.payload!!)
            } else {
                Result.Error(body.error!!.message, body.error.code)
            }
        } else {
            Result.Error(response.message(), -2)
        }
    } catch (e: Exception) {
        Result.Error(e.toString(), -1)
    }

    override suspend fun getOrderBook(book: String): Result<OrderBook> = try {
        val response = coinsApi.getOrderBook()

        if (response.isSuccessful) {
            val body = response.body()!!
            if (body.success) {
                Result.Success(body.payload!!)
            } else {
                Result.Error(body.error!!.message, body.error.code)
            }
        } else {
            Result.Error(response.message(), -2)
        }
    } catch (e: Exception) {
        Result.Error(e.toString(), -1)
    }
}