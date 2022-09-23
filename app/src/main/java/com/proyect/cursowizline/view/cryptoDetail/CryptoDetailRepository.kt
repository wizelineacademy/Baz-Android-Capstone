package com.proyect.cursowizline.view.cryptoDetail

import com.proyect.cursowizline.api.CryptoApi.retrofitService
import com.proyect.cursowizline.api.makeNetworkCall
import com.proyect.cursowizline.model.CryptoOrder
import com.proyect.cursowizline.model.CryptoOrderMapDTO
import com.proyect.cursowizline.model.ResponseStatus

class CryptoDetailRepository {
    suspend fun downloadCryptoOrder(): ResponseStatus<List<CryptoOrder>> {
        return makeNetworkCall {
            val cryptoOrderListResponde = retrofitService.getOrderCrypto("btc_mxn")
            val cryptoOrderDTOList = cryptoOrderListResponde.payload.asks
            val cryptoOrderDTOMapper = CryptoOrderMapDTO()
            cryptoOrderDTOMapper.fromCryptoOrderDTOListToCryptoOrderDomainList(cryptoOrderDTOList)
        }
    }
}