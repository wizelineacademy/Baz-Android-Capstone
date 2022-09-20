package com.ari.coins.framework.data.network.dataSources

import com.ari.coins.data.models.*
import com.ari.coins.data.network.dataSource.CoinsRemoteDataSource
import com.ari.coins.framework.data.network.apis.CoinsApi
import retrofit2.Response
import javax.inject.Inject

class CoinsRemoteDataSourceImpl @Inject constructor(
    private val coinsApi: CoinsApi
): CoinsRemoteDataSource {

    override suspend fun getAvailableBooks(): Result<List<AvailableBook>> = try {
        val response: Response<CryptoResponse<List<AvailableBook>>> = coinsApi.getAvailableBooks()

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
        val response: Response<CryptoResponse<Ticker>> = coinsApi.getTicker(book)

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
        val response: Response<CryptoResponse<OrderBook>> = coinsApi.getOrderBook(book)

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