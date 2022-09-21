package com.vero.cursowizelinecriptomonedas.api.dto

import com.vero.cursowizelinecriptomonedas.model.CryptoOrder

class CryptoOrderDTOMapper {
    private fun fromCryptoOrderDTOToCryptoOrderDomain(cryptoOrderDTO: CryptoOrderDTO): CryptoOrder {
        return CryptoOrder(
            cryptoOrderDTO.book,
            cryptoOrderDTO.price,
            cryptoOrderDTO.amount
        )
    }

    fun fromCryptoOrderDTOListToCryptoOrderDomainList(cryptoDTOList: List<CryptoOrderDTO>): List<CryptoOrder> {
        return cryptoDTOList.map { fromCryptoOrderDTOToCryptoOrderDomain(it) }
    }
}