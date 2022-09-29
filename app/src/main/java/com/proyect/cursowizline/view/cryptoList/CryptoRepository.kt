package com.proyect.cursowizline.view.cryptoList

import com.proyect.cursowizline.api.ApiService
import com.proyect.cursowizline.api.makeNetworkCall
import com.proyect.cursowizline.database.dao.CryptoDao
import com.proyect.cursowizline.database.entities.CryptoEntity
import com.proyect.cursowizline.domain.model.CryptoM
import com.proyect.cursowizline.domain.model.toDomain
import com.proyect.cursowizline.model.Crypto
import com.proyect.cursowizline.model.CryptoMapDTO
import com.proyect.cursowizline.model.ResponseStatus
import javax.inject.Inject


class CryptoRepository @Inject constructor(private val cryptoListResponde : ApiService, private val cryptoDao: CryptoDao ){
    suspend fun downloadCrypto(): ResponseStatus<List<CryptoM>> {
        return makeNetworkCall {
            cryptoListResponde.getAllCrypto()
            cryptoListResponde.getAllCrypto().payload
            val cryptoDTOMapper = CryptoMapDTO()
            cryptoDTOMapper.fromCryptoDTOListToCryptoDomainList(
                cryptoListResponde.getAllCrypto().payload
            )
        }
    }

    suspend fun getAllCryptoFromDatabase():List<CryptoM>{
        val response: List<CryptoEntity> = cryptoDao.getAllCrypto()
        return response.map { it.toDomain() }
}

    suspend fun insertCrypto(crypto: List<CryptoEntity>){
        cryptoDao.insertAll(crypto)
    }

    suspend fun clearCrypto(){
        cryptoDao.deleteAllCrypto()
    }
}