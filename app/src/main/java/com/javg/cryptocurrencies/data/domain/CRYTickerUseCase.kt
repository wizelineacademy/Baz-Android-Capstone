package com.javg.cryptocurrencies.data.domain

import com.javg.cryptocurrencies.data.model.CRYDetailBook
import com.javg.cryptocurrencies.data.repository.CRYOrderBookRepository
import com.javg.cryptocurrencies.data.repository.CRYTickerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CRYTickerUseCase @Inject constructor(
    private val tickerRepository: CRYTickerRepository,
    private val orderBookRepository: CRYOrderBookRepository) {

    suspend operator fun invoke(book: String): CRYDetailBook = withContext(Dispatchers.IO){
        val ticker = tickerRepository.getTicker(book)
        val detailBook = CRYDetailBook()

        ticker.payload?.let {
            val orderBook = orderBookRepository.getOrderBook(book)
            detailBook.apply {
                last = ticker.payload?.last.orEmpty()
                high = ticker.payload?.high.orEmpty()
                low  = ticker.payload?.low.orEmpty()
                askList = orderBook.payload?.asksList
                bidsList = orderBook.payload?.bidsList
            }
        }
        detailBook
    }
}