package com.jpgl.cryptocurrencies.domain

import com.jpgl.cryptocurrencies.data.CryptoRepository
import com.jpgl.cryptocurrencies.data.database.entities.toDatabase
import com.jpgl.cryptocurrencies.data.model.BidsModel
import com.jpgl.cryptocurrencies.data.model.BookModel
import com.jpgl.cryptocurrencies.domain.model.BidsModelDomain
import com.jpgl.cryptocurrencies.domain.model.BooksModelDomain
import javax.inject.Inject

class GetBidsUseCase @Inject constructor(
    private val cryptoRepository : CryptoRepository
) {
    suspend operator fun invoke(book: String): List<BidsModelDomain> {
        val bids =  cryptoRepository.getAllBidsFromApi(book)
        return if (bids.isNotEmpty()) {
            cryptoRepository.cleanBids()
            cryptoRepository.insertBids( bids.map { it.toDatabase() })
            bids
        } else {
            //si falla el servidor se accede a una versión guardada en la base de datos
            cryptoRepository.getAllBidsFromDatabase()
        }
    }
}