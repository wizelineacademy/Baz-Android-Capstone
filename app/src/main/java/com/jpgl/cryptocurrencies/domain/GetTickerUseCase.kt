package com.jpgl.cryptocurrencies.domain


import com.jpgl.cryptocurrencies.data.CryptoRepository
import com.jpgl.cryptocurrencies.data.database.entities.toDatabase
import com.jpgl.cryptocurrencies.data.model.BidsModel
import com.jpgl.cryptocurrencies.data.model.TickerModel
import com.jpgl.cryptocurrencies.domain.model.BidsModelDomain
import com.jpgl.cryptocurrencies.domain.model.TickerModelDomain
import javax.inject.Inject

class GetTickerUseCase @Inject constructor(
    private val cryptoRepository : CryptoRepository
){

    suspend operator fun invoke(book: String): TickerModelDomain? {
        val ticker =  cryptoRepository.getAllTickerFromApi(book)
        return if (ticker != null) {
            cryptoRepository.cleanTicker()
            cryptoRepository.insertTicker(ticker.toDatabase())
            ticker
        } else {
            //si falla el servidor se accede a una versión guardada en la base de datos
            cryptoRepository.getAllTickerFromDatabase()
        }
    }
}
