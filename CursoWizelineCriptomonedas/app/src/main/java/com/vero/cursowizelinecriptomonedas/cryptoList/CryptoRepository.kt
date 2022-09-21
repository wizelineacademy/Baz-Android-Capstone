package com.vero.cursowizelinecriptomonedas.cryptoList

import com.vero.cursowizelinecriptomonedas.R
import com.vero.cursowizelinecriptomonedas.api.ApiResponseStatus
import com.vero.cursowizelinecriptomonedas.api.CryptoApi.retrofitService
import com.vero.cursowizelinecriptomonedas.dto.CryptoDTOMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

class CryptoRepository {
    suspend fun downloadCrypto(): ApiResponseStatus {
        return withContext(Dispatchers.IO) {
            try {
                val cryptoListApiResponde = retrofitService.getAllCrypto()
                val cryptoDTOList = cryptoListApiResponde.payload
                val cryptoDTOMapper = CryptoDTOMapper()
                ApiResponseStatus.Success(
                    cryptoDTOMapper.fromCryptoDTOListToCryptoDomainList(
                        cryptoDTOList
                    )
                )
            } catch (e: UnknownHostException) {
                ApiResponseStatus.Error(R.string.error_unknown_host_exception)
            } catch (e: Exception) {
                ApiResponseStatus.Error(R.string.error_unknown)
            }

        }
    }
}