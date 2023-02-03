package com.example.cryptocurrencyapp.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.cryptocurrencyapp.data.remote.api.CryptoApi
import com.example.cryptocurrencyapp.data.database.data_source.CryptoLocalDataSource
import com.example.cryptocurrencyapp.data.database.entities.*
import com.example.cryptocurrencyapp.data.remote.data_source.WCCryptoRepositoryImp
import com.example.cryptocurrencyapp.domain.entity.*
import com.example.cryptocurrencyapp.domain.repository.WCCryptoRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CryptoRespository @Inject constructor(
    @ApplicationContext private val context: Context,
    private val api: CryptoApi,
    private val localDataSource: CryptoLocalDataSource,
    private val remoteDataSource: WCCryptoRepositoryImp,
) : WCCryptoRepository {
    override suspend fun getAvailableBooks(): List<WCCryptoBookDTO> {
        if (isInternetAvailable(context)) {
            val cryptoList = remoteDataSource.getAvailableBooks()
            if (cryptoList.isNotEmpty()) {
                localDataSource.insertAvailableBookToDB(cryptoList.toAvailableEntity())
            } else {
                localDataSource.getAllAvailableFromDB()
            }
            return cryptoList
        } else {
            return try {
                localDataSource.getAllAvailableFromDB().map {
                    it.toWCCryptoBookDTO()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }
        }
    }


    override suspend fun getTickerBook(book: String): WCCTickerDTO {
        return if (isInternetAvailable(context)) {
            val ticker: WCCTickerDTO = remoteDataSource.getTickerBook(book)

            if (ticker.book != "") {
                localDataSource.insertTickerToDB(ticker.toTickerEntity())
                ticker.toTickerEntity().toWCCTickerDTO()
            } else
                localDataSource.getTickerFromDB(book).toWCCTickerDTO()
        } else
            try {
                localDataSource.getTickerFromDB(book).toWCCTickerDTO()
            } catch (e: Exception) {
                e.printStackTrace()
                return WCCTickerDTO()
            } }

    override suspend fun getOrderBook(book: String): WCCOrdeRDTO {
        if (isInternetAvailable(context)) {
            val order = remoteDataSource.getOrderBook(book)
            if (order.ask.isNotEmpty() && order.bids.isNotEmpty()) {
                localDataSource.deleteOrderBook(book)
                localDataSource.insertOrderBookDB(
                    order.ask.toAskEntityList(),
                    order.bids.toBidsEntityList()
                )
            }
            return order
        } else {
            return try {
                localDataSource.getOrderBookFromDB(book)
            } catch (e: Exception) {
                e.printStackTrace()
                WCCOrdeRDTO()
            }
        }
    }
}

fun isInternetAvailable(context: Context): Boolean {
    var result = false
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    } else {
        connectivityManager.run {
            connectivityManager.activeNetworkInfo?.run {
                result = when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }

            }
        }
    }

    return result
}



