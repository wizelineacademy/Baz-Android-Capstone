package com.proyect.cursowizline.view.cryptoList

import com.proyect.cursowizline.api.ApiService
import com.proyect.cursowizline.api.makeNetworkCall
import com.proyect.cursowizline.model.Crypto
import com.proyect.cursowizline.model.CryptoMapDTO
import com.proyect.cursowizline.model.ResponseStatus
import javax.inject.Inject


class CryptoRepository @Inject constructor(private val cryptoListResponde : ApiService ){
    suspend fun downloadCrypto(): ResponseStatus<List<Crypto>> {
        return makeNetworkCall {
            cryptoListResponde.getAllCrypto()
            cryptoListResponde.getAllCrypto().payload
            val cryptoDTOMapper = CryptoMapDTO()
            cryptoDTOMapper.fromCryptoDTOListToCryptoDomainList(
                cryptoListResponde.getAllCrypto().payload
            )
        }
    }
}