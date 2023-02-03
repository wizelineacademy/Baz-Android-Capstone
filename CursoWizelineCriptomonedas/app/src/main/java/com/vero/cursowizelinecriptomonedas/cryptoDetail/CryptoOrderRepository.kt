package com.vero.cursowizelinecriptomonedas.cryptoDetail

import com.vero.cursowizelinecriptomonedas.api.ApiResponseStatus
import com.vero.cursowizelinecriptomonedas.api.CryptoApi.retrofitService
import com.vero.cursowizelinecriptomonedas.api.dto.CryptoOrderDTOMapper
import com.vero.cursowizelinecriptomonedas.api.makeNetworkCall
import com.vero.cursowizelinecriptomonedas.model.CryptoOrder

class CryptoOrderRepository {
    //TODO : ApiResponseStatus
    suspend fun downloadCryptoOrder(crypto: String): ApiResponseStatus<List<CryptoOrder>> {
        return makeNetworkCall {
            val cryptoOrderListApiResponde = retrofitService.getOrderCrypto(crypto)
            val cryptoOrderDTOList = cryptoOrderListApiResponde.payload.asks
            val cryptoOrderDTOMapper = CryptoOrderDTOMapper()
            cryptoOrderDTOMapper.fromCryptoOrderDTOListToCryptoOrderDomainList(cryptoOrderDTOList)
        }
    }
}