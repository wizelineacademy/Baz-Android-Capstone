package com.proyect.cursowizline.model

class CryptoOrderMapDTO {

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