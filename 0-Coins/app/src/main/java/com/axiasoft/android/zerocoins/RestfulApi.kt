package com.axiasoft.android.zerocoins

import com.axiasoft.android.zerocoins.network.apis.CoinApis
import com.axiasoft.android.zerocoins.network.connections.HttpConnectionManager
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.apis.BitsoOrderBooksApi
import retrofit2.Call
import retrofit2.http.GET

interface RestfulApi {
    @GET("/objects")
    fun getAllDevices(): Call<List<Device>>


    @GET("/objects")
    fun getAllDevicesOnNaked(): List<Device>



    class Builder : HttpConnectionManager<RestfulApi>(CoinApis.RESTFUL_API) {
        override fun build(): RestfulApi {
            return httpClient.create(RestfulApi::class.java)
        }
    }
}