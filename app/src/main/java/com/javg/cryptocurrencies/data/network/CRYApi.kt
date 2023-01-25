package com.javg.cryptocurrencies.data.network

import com.javg.cryptocurrencies.config.CRYConstant
import com.javg.cryptocurrencies.data.model.CRYBaseResponseModel
import retrofit2.http.GET

interface CRYApi {
    @GET(CRYConstant.END_POINT_AVAILABLE_BOOKS)
    suspend fun getListAvailableBooks(): CRYBaseResponseModel
}