package com.vero.cursowizelinecriptomonedas.cryptoDetail

import com.vero.cursowizelinecriptomonedas.api.ApiResponseStatus
import com.vero.cursowizelinecriptomonedas.api.CryptoApi.retrofitService
import com.vero.cursowizelinecriptomonedas.api.dto.CryptoOrderDTOMapper
import com.vero.cursowizelinecriptomonedas.api.makeNetworkCall
import com.vero.cursowizelinecriptomonedas.model.CryptoOrder

class CryptoOrderRepository {
    //TODO : ApiResponseStatus
    suspend fun downloadCryptoOrder(): ApiResponseStatus<List<CryptoOrder>> {
        return makeNetworkCall {
            val cryptoOrderListApiResponde = retrofitService.getOrderCrypto("btc_mxn")
            val cryptoOrderDTOList = cryptoOrderListApiResponde.payload.asks
            val cryptoOrderDTOMapper = CryptoOrderDTOMapper()
            cryptoOrderDTOMapper.fromCryptoOrderDTOListToCryptoOrderDomainList(cryptoOrderDTOList)
        }
    }
}