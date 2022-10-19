package com.example.cryptocurrency_challenge.data.network

import com.example.cryptocurrency_challenge.data.model.*
import com.example.cryptocurrency_challenge.room.RoomEntityDao
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class NetworkDataSourceImpl @Inject constructor (
    private val apiBitsoService: ApiBitsoService,
    private val appDataBase : RoomEntityDao)
    : NetworkDataSource {

        override suspend fun getAvailableBooksRX(): Response<AvailableBooksResponse> =
            suspendCoroutine { coroutine ->
                apiBitsoService.getAllCurrenciesRX()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { response ->
                        if (response.isSuccessful) {
                            coroutine.resume(response)
                        }else{
                            coroutine.resume(response.apply {
                                AvailableBooksResponse(
                                    payload = listOf(Payload()),
                                    success = false
                                )

                            })
                        }
                    }
            }

        override suspend fun getTicker(currency_name: String?): Response<InfoTickerResponse> {
            val responseAux = apiBitsoService.getInfoTicker(currency_name!!)
             if (responseAux.isSuccessful){
                CoroutineScope((Dispatchers.IO)).launch {
                    responseAux.body()?.payload?.let { appDataBase.insertTicker(it.asExternalEntity()) }
                }

            }
            return responseAux
        }

        override suspend fun getOrderBook(currency_name: String): Response<OrderBookResponse> =
            apiBitsoService.getOrderBook(currency_name)

    }