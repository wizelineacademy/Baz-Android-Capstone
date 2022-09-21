package com.vero.cursowizelinecriptomonedas.cryptoList

import com.vero.cursowizelinecriptomonedas.Crypto
import com.vero.cursowizelinecriptomonedas.api.ApiResponseStatus
import com.vero.cursowizelinecriptomonedas.api.CryptoApi.retrofitService
import com.vero.cursowizelinecriptomonedas.api.dto.CryptoDTOMapper
import com.vero.cursowizelinecriptomonedas.api.makeNetworkCall

class CryptoRepository {
    suspend fun downloadCrypto(): ApiResponseStatus<List<Crypto>> {
        return makeNetworkCall {
            val cryptoListApiResponde = retrofitService.getAllCrypto()
            val cryptoDTOList = cryptoListApiResponde.payload
            val cryptoDTOMapper = CryptoDTOMapper()
            cryptoDTOMapper.fromCryptoDTOListToCryptoDomainList(
                cryptoDTOList
            )
        }
    }
}