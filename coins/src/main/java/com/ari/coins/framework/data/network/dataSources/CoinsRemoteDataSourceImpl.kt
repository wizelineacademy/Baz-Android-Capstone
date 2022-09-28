package com.ari.coins.framework.data.network.dataSources

import com.ari.coins.data.models.AvailableBookData
import com.ari.coins.data.models.OrderBookData
import com.ari.coins.data.models.ResultData
import com.ari.coins.data.models.TickerData
import com.ari.coins.data.network.CoinsRemoteDataSource
import com.ari.coins.framework.data.network.apis.CoinsApi
import com.ari.coins.framework.data.network.toResult
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class CoinsRemoteDataSourceImpl @Inject constructor(
    private val coinsApi: CoinsApi
) : CoinsRemoteDataSource {

    override suspend fun getAvailableBooks(): ResultData<List<AvailableBookData>> = try {
        val response = coinsApi.getAvailableBooks()
        if (response.isSuccessful) {
            val body = response.body()!!
            if (body.success) ResultData.Success(body.payload!!)
            else ResultData.Error(body.error!!.message, body.error.code)
        } else {
            ResultData.Error(response.message(), -1)
        }
    } catch (e: UnknownHostException) {
        ResultData.Error(e.toString(), -11)
    } catch (e: ConnectException) {
        ResultData.Error(e.toString(), -111)
    } catch (e: Exception) {
        ResultData.Error(e.toString(), -1111)
    }

    override suspend fun getTicker(book: String): ResultData<TickerData> = try {
        val response = coinsApi.getTicker(book)
        if (response.isSuccessful) {
            val body = response.body()!!
            if (body.success) ResultData.Success(body.payload!!)
            else ResultData.Error(body.error!!.message, body.error.code)
        } else {
            ResultData.Error(response.message(), -1)
        }
    } catch (e: UnknownHostException) {
        ResultData.Error(e.toString(), -11)
    } catch (e: ConnectException) {
        ResultData.Error(e.toString(), -111)
    } catch (e: Exception) {
        ResultData.Error(e.toString(), -1111)
    }

    override suspend fun getOrderBook(book: String): ResultData<OrderBookData> = try {
        val response = coinsApi.getOrderBook(book)
        if (response.isSuccessful) {
            val body = response.body()!!
            if (body.success) ResultData.Success(body.payload!!)
            else ResultData.Error(body.error!!.message, body.error.code)
        } else {
            ResultData.Error(response.message(), -1)
        }
    } catch (e: UnknownHostException) {
        ResultData.Error(e.toString(), -11)
    } catch (e: ConnectException) {
        ResultData.Error(e.toString(), -111)
    } catch (e: Exception) {
        ResultData.Error(e.toString(), -1111)
    }

}