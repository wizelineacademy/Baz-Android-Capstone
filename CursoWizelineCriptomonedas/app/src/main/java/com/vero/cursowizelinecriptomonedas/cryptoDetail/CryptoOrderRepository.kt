package com.vero.cursowizelinecriptomonedas.cryptoDetail

import com.vero.cursowizelinecriptomonedas.model.CryptoOrder
import com.vero.cursowizelinecriptomonedas.api.CryptoApi.retrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CryptoOrderRepository {
    //TODO : ApiResponseStatus
    suspend fun downloadCryptoOrder(): List<CryptoOrder> {
        return withContext(Dispatchers.IO) {
            val cryptoOrderListApiResponde = retrofitService.getOrderCrypto("btc_mxn")
            cryptoOrderListApiResponde.payload.asks
        }
    }
}