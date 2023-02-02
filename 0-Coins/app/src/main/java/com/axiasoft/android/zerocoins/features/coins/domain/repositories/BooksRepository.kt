package com.axiasoft.android.zerocoins.features.coins.domain.repositories

import com.axiasoft.android.zerocoins.features.coins.domain.models.data.book.response.Book
import com.axiasoft.android.zerocoins.network.bitso.models.BitsoBaseResponse
import com.axiasoft.android.zerocoins.network.bitso.wrappers.BitsoApiResponseWrap

interface BooksRepository {
    suspend fun getBooksFromApi(): BitsoApiResponseWrap<BitsoBaseResponse<ArrayList<Book>>>
}