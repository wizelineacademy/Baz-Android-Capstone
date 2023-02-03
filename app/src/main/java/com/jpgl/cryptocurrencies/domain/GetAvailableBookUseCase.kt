package com.jpgl.cryptocurrencies.domain

import com.jpgl.cryptocurrencies.data.CryptoRepository
import com.jpgl.cryptocurrencies.data.database.entities.toDatabase
import com.jpgl.cryptocurrencies.data.model.BookModel
import com.jpgl.cryptocurrencies.domain.model.BooksModelDomain
import javax.inject.Inject

class GetAvailableBookUseCase @Inject constructor(
    private val cryptoRepository : CryptoRepository
){
    suspend operator fun invoke(): List<BooksModelDomain> {
        val books =  cryptoRepository.getAllAvailableBooksFromApi()
        return if (books.isNotEmpty()) {
            cryptoRepository.cleanAvailableBooks()
            cryptoRepository.insertAvailableBooks( books.map { it.toDatabase() })
            books
        } else {
            //si falla el servidor se accede a una versión guardada en la base de datos
            cryptoRepository.getAllAvailableBooksFromDatabase()
        }
    }
}