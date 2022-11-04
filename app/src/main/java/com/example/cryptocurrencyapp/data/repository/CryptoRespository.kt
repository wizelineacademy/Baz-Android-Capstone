package com.example.cryptocurrencyapp.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.cryptocurrencyapp.data.database.data_source.CryptoLocalDataSource
import com.example.cryptocurrencyapp.data.database.entities.AvailableBookEntity
import com.example.cryptocurrencyapp.data.remote.data_source.WCCryptoRepositoryImp
import com.example.cryptocurrencyapp.data.remote.entity.response.WCCryptoAvailableResponse
import com.example.cryptocurrencyapp.domain.entity.WCCOrdeRDTO
import com.example.cryptocurrencyapp.domain.entity.WCCTickerDTO
import com.example.cryptocurrencyapp.domain.entity.WCCryptoBookDTO
import com.example.cryptocurrencyapp.domain.repository.WCCryptoRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CryptoRespository @Inject constructor(
    @ApplicationContext private val context: Context,
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
                            minPrice = it.minPrice,
                            maxPrice = it.maxPrice,
                            logo = it.logo
                        )
                    }
                )
            }
            return cryptoList
        } else {
            try{
                return localDataSource.getAllAvailableFromDB().map {
                    WCCryptoBookDTO(
                        book = it.book,
                        minPrice = it.minPrice,
                        maxPrice = it.maxPrice,
                        logo = it.logo
                    )
                }
            }catch (e: Exception){
                e.printStackTrace()
                return emptyList()
            }
        }
    }

    override suspend fun getTickerBook(book: String): WCCTickerDTO {
        TODO("Not yet implemented")
    }

    override suspend fun getOrderBook(book: String): WCCOrdeRDTO {
        TODO("Not yet implemented")
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



