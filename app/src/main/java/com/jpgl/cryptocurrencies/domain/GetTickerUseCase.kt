package com.jpgl.cryptocurrencies.domain


import com.jpgl.cryptocurrencies.data.CryptoRepository
import com.jpgl.cryptocurrencies.data.model.BidsModel
import com.jpgl.cryptocurrencies.data.model.TickerModel
import javax.inject.Inject

class GetTickerUseCase @Inject constructor(
    private val cryptoRepository : CryptoRepository
){
    suspend operator fun invoke(): TickerModel = cryptoRepository.getAllTicker()
}