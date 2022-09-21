package com.vero.cursowizelinecriptomonedas.cryptoList

import com.vero.cursowizelinecriptomonedas.Crypto
import com.vero.cursowizelinecriptomonedas.api.CryptoApi.retrofitService
import com.vero.cursowizelinecriptomonedas.dto.CryptoDTOMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CryptoRepository {
    suspend fun downloadCrypto(): List<Crypto> {
        return withContext(Dispatchers.IO) {
            val cryptoListApiResponde = retrofitService.getAllCrypto()
            val cryptoDTOList = cryptoListApiResponde.payload
            val cryptoDTOMapper = CryptoDTOMapper()
            cryptoDTOMapper.fromCryptoDTOListToCryptoDomainList(cryptoDTOList)
        }
    }
}