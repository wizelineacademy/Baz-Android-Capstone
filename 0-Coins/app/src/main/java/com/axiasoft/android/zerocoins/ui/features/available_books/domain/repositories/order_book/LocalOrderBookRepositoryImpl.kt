package com.axiasoft.android.zerocoins.ui.features.available_books.domain.repositories.order_book

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalOrderBookRepositoryImpl: LocalOrderBookRepository {

    suspend fun storeAvailableExchangeOrderBooks(){
        withContext(Dispatchers.IO){

        }
    }
}