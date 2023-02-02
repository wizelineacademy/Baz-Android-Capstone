package com.javg.cryptocurrencies.data.domain

import com.javg.cryptocurrencies.data.model.CRYDetailBook
import com.javg.cryptocurrencies.data.repository.CRYOrderBookRepository
import com.javg.cryptocurrencies.data.repository.CRYTickerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CRYGetListBookWithTickerUseCase @Inject constructor(
    private val tickerRepository: CRYTickerRepository,
    private val orderBookRepository: CRYOrderBookRepository) {

    suspend operator fun invoke(book: String): CRYDetailBook = withContext(Dispatchers.IO){
        val ticker = tickerRepository.getTicker(book)
        val detailBook = CRYDetailBook()

        if (ticker.isSuccessful){
            ticker.body()?.payload?.let {
                val orderBook = orderBookRepository.getOrderBook(book)
                detailBook.apply {
                    last = it.last
                    high = it.high
                    low  = it.low
                    askList = orderBook.body()?.payload?.asksList
                    bidsList = orderBook.body()?.payload?.bidsList
                }
            }
        }
        detailBook
    }
}