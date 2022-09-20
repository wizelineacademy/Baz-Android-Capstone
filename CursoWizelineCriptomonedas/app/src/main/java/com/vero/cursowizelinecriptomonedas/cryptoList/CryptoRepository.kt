package com.vero.cursowizelinecriptomonedas.cryptoList

import com.vero.cursowizelinecriptomonedas.Crypto
import com.vero.cursowizelinecriptomonedas.api.CryptoApi.retrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CryptoRepository {
    suspend fun downloadCrypto(): List<Crypto> {
        return withContext(Dispatchers.IO) {
            val cryptoListApiResponde = retrofitService.getAllCrypto()
            cryptoListApiResponde.payload
        }
    }
}