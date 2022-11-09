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
                localDataSource.insertAvailableBookToDB(
                    cryptoList.map {
                        AvailableBookEntity(
                            book = it.book,
                            name = it.name,
                            minPrice = it.minPrice,
                            maxPrice = it.maxPrice,
                            logo = it.logo
                        )
                    }
                )
            }
            return cryptoList
        } else {
            try {
                val a=localDataSource.getAllAvailableFromDB().map {
                    WCCryptoBookDTO(
                        book = it.book,
                        name = it.name,
                        minPrice = it.minPrice,
                        maxPrice = it.maxPrice,
                        logo = it.logo
                    )
                }
                return  a
            } catch (e: Exception) {
                e.printStackTrace()
                return emptyList()
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
            localDataSource.getTickerFromDB(book).toWCCTickerDTO()
    }

    override suspend fun getOrderBook(book: String): WCCOrdeRDTO {
        if (isInternetAvailable(context)) {
            val order = remoteDataSource.getOrderBook(book)
            if (order.ask.isNotEmpty() && order.bids.isNotEmpty()) {
                localDataSource.deteOrderBook(book)
                localDataSource.insertOrderBookDB(
                    order.ask.toAskEntityList(),
                    order.bids.toBidsEntityList()
                )
            }
            /*if (order.ask.isNotEmpty() && order.bids.isNotEmpty()){
               // localDataSource.deletList(book)
               localDataSource.insertOrdertoDB(order.ask.map {
                        AskEntity(
                            book = it.book,
                            price = it.price,
                            amount = it.amount,
                            type = it.type
                        )
                }, order.bids.map { bid->
                    BidEntity(
                        book = bid.book,
                        price = bid.price,
                        amount = bid.amount,
                        type = bid.type
                    )
                }.toMutableList())
                //localDataSource.insertOrdertoDB(order.ask.toAaskEntityList(),order.bids.toBidEnttyList())
            }else{
                localDataSource.getOrderFromDB(book)
            }*/
            return order
        } else {
            return localDataSource.getOrderBookDB(book)

            //WCCOrdeRDTO() //localDataSource.getOrderFromDB(book)

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



