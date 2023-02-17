package com.axiasoft.android.zerocoins.ui.features.available_books.domain.repositories.order_book

import com.axiasoft.android.zerocoins.network.bitso.models.BitsoBaseResponse
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.apis.BitsoOrderBooksApi
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.ticker.Ticker
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.ticker.response.TickerResponse
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.whenever

class RemoteOrderBooksRepositoryGetTickerTest {

    private lateinit var remoteOrderBooksRepositoryImpl: RemoteOrderBooksRepositoryImpl

    private val mockSelectedBookKey = "mana_btc"

    private val successMockTickerResponse = BitsoBaseResponse(
        success = true,
        payload = Ticker(
            book = "mana_btc"
        )
    )

    @Mock
    private lateinit var bitsoOrderBooksApi: BitsoOrderBooksApi

    @Test
    fun `test remote repository getTickerFromApi success`(){
        runBlocking {
            whenever(bitsoOrderBooksApi.getTicketsApi(mockSelectedBookKey)).thenReturn(successMockTickerResponse)
        }
    }
}