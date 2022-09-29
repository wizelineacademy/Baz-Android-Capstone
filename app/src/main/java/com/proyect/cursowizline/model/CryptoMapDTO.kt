package com.proyect.cursowizline.model

import com.proyect.cursowizline.domain.model.CryptoM

class CryptoMapDTO {

    private fun fromCryptoDTOToCryptoDomain(cryptoDTO: CryptoDTO): CryptoM {
        return CryptoM(
            cryptoDTO.book,
            cryptoDTO.minimum_price,
            cryptoDTO.maximum_price,
            cryptoDTO.minimum_value,
            cryptoDTO.maximum_value,
            cryptoDTO.tick_size
        )
    }

    fun fromCryptoDTOListToCryptoDomainList(cryptoDTOList: List<CryptoDTO>): List<CryptoM> {
        return cryptoDTOList.map { fromCryptoDTOToCryptoDomain(it) }
    }
}