package com.javg.cryptocurrencies.data.domain

import com.javg.cryptocurrencies.data.model.CRYDetailBook
import com.javg.cryptocurrencies.data.repository.CRYOrderBookRepository
import com.javg.cryptocurrencies.data.repository.CRYTickerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @author Juan Vera Gomez
 * Date modified 13/02/2023
 *
 * Contains the functionality to query the information and make
 * a deal before returning the information to the view model
 *
 * @param tickerRepository is the repository that returns only ticker information
 * @param orderBookRepository is the repository that returns only order book information
 *
 * @since 2.0
 */
class CRYGetListBookWithTickerUseCase @Inject constructor(
    private val tickerRepository: CRYTickerRepository,
    private val orderBookRepository: CRYOrderBookRepository) {

    /**
     * Returns a detail type model of a specific book,
     * obtaining the information from two different repositories
     *
     * @param book is the name of the book to consult its information
     *
     * @return CRYDetailBook is the detail model of the consulted book
     */
    suspend operator fun invoke(book: String): CRYDetailBook? = withContext(Dispatchers.IO){
        val ticker = tickerRepository.getTicker(book)
        var detailBook: CRYDetailBook? = null

        if (ticker != null)
            detailBook = orderBookRepository.getOrderBook(book)

        detailBook
    }
}