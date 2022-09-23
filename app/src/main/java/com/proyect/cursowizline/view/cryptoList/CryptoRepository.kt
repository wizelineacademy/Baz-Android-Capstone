package com.proyect.cursowizline.view.cryptoList

import com.proyect.cursowizline.api.CryptoApi.retrofitService
import com.proyect.cursowizline.api.makeNetworkCall
import com.proyect.cursowizline.model.Crypto
import com.proyect.cursowizline.model.CryptoMapDTO
import com.proyect.cursowizline.model.ResponseStatus

class CryptoRepository {
    suspend fun downloadCrypto(): ResponseStatus<List<Crypto>> {
        return makeNetworkCall {
            val cryptoListResponde = retrofitService.getAllCrypto()
            val cryptoDTOList = cryptoListResponde.payload
            val cryptoDTOMapper = CryptoMapDTO()
            cryptoDTOMapper.fromCryptoDTOListToCryptoDomainList(
                cryptoDTOList
            )
        }
    }
}