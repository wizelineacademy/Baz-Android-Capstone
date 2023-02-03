package com.vero.cursowizelinecriptomonedas.api.dto

import com.vero.cursowizelinecriptomonedas.model.Crypto

class CryptoDTOMapper {
    private fun fromCryptoDTOToCryptoDomain(cryptoDTO: CryptoDTO): Crypto {
        return Crypto(
            cryptoDTO.book,
            cryptoDTO.minimum_price,
            cryptoDTO.maximum_price,
            cryptoDTO.minimum_amount,
            cryptoDTO.maximum_amount,
            cryptoDTO.minimum_value,
            cryptoDTO.maximum_value,
            cryptoDTO.tick_size
        )
    }

    fun fromCryptoDTOListToCryptoDomainList(cryptoDTOList: List<CryptoDTO>): List<Crypto> {
        return cryptoDTOList.map { fromCryptoDTOToCryptoDomain(it) }
    }
}