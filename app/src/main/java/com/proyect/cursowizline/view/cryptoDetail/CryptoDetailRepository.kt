package com.proyect.cursowizline.view.cryptoDetail

import com.proyect.cursowizline.api.ApiService
import com.proyect.cursowizline.api.makeNetworkCall
import com.proyect.cursowizline.model.CryptoOrder
import com.proyect.cursowizline.model.CryptoOrderMapDTO
import com.proyect.cursowizline.model.ResponseStatus
import javax.inject.Inject

class CryptoDetailRepository @Inject constructor(private val cryptoOrderListResponde: ApiService ){
    suspend fun downloadCryptoOrder(): ResponseStatus<List<CryptoOrder>> {
        return makeNetworkCall {
            cryptoOrderListResponde.getOrderCrypto("btc_mxn")
            cryptoOrderListResponde.getOrderCrypto("btc_mxn").payload.asks
            val cryptoOrderDTOMapper = CryptoOrderMapDTO()
            cryptoOrderDTOMapper.fromCryptoOrderDTOListToCryptoOrderDomainList(cryptoOrderListResponde.getOrderCrypto("btc_mxn").payload.asks)
        }
    }
}