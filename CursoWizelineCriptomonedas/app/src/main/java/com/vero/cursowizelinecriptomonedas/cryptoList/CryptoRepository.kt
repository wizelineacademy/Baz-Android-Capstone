package com.vero.cursowizelinecriptomonedas.cryptoList

import com.vero.cursowizelinecriptomonedas.Crypto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CryptoRepository {
    suspend fun downloadCrypto(): List<Crypto>{
        return withContext(Dispatchers.IO){
            getFakeCrypto()
        }
    }
    private fun getFakeCrypto(): MutableList<Crypto> {
        val cryptoList = mutableListOf<Crypto>()
        cryptoList.add(
            Crypto( "btc_mxn", 10.1, 10.9, 10.2, 10.8, 10.3, 10.7, 11.0)
        )
        cryptoList.add(
            Crypto("eth_btc", 12.1, 12.9, 12.2, 12.8, 12.3, 12.7, 13.0)
        )
        cryptoList.add(
            Crypto("eth_mxn", 14.1, 14.9, 14.2, 14.8, 14.3, 14.7, 15.0)
        )
        cryptoList.add(
            Crypto("xrp_btc", 16.1, 16.9, 16.2, 16.8, 16.3, 16.7, 17.0)
        )
        return cryptoList
    }

}