package com.axiasoft.android.zerocoins.ui.features.available_books.domain.repositories.order_book

import com.axiasoft.android.zerocoins.network.bitso.models.BitsoBaseResponse
import com.axiasoft.android.zerocoins.network.bitso.wrappers.BitsoApiResponseWrap
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.apis.BitsoOrderBooksApi
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.open_orders_book.Ask
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.open_orders_book.Bids
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.open_orders_book.response.ListOrderBookResponse
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class RemoteOrderBooksRepositoryGetAsksAndBidsTest {

    private lateinit var remoteOrderBooksRepositoryImpl: RemoteOrderBooksRepositoryImpl

    private val mockSelectedBookKey = "mana_btc"

    private val successOpenOrderBook = BitsoBaseResponse(
        success = true,
        payload = ListOrderBookResponse(
            asks = arrayListOf(
                Ask(book = "mana_btc", price = "0.01222", "33"),
                Ask(book = "mana_btc", price = "0.01222", "33"),
                Ask(book = "mana_btc", price = "0.01222", "33"),
                Ask(book = "mana_btc", price = "0.01222", "33"),
                Ask(book = "mana_btc", price = "0.01222", "33"),
                Ask(book = "mana_btc", price = "0.01222", "33"),
                Ask(book = "mana_btc", price = "0.01222", "33"),
            ),
            bids = arrayListOf(
                Bids(book = "mana_btc", price = "0.01222", "33"),
                Bids(book = "mana_btc", price = "0.01222", "33"),
                Bids(book = "mana_btc", price = "0.01222", "33"),
                Bids(book = "mana_btc", price = "0.01222", "33"),
                Bids(book = "mana_btc", price = "0.01222", "33"),
                Bids(book = "mana_btc", price = "0.01222", "33"),
                Bids(book = "mana_btc", price = "0.01222", "33"),
            ),
        ),
    )

    @Mock
    private lateinit var bitsoOrderBooksApi: BitsoOrderBooksApi

    @Before
    fun setup() {
        bitsoOrderBooksApi = mock()
        remoteOrderBooksRepositoryImpl = RemoteOrderBooksRepositoryImpl(bitsoOrderBooksApi)
    }

    @Test
    fun `test remote repository get open orders success`() {
        runBlocking {
            whenever(bitsoOrderBooksApi.getListOrderBook(mockSelectedBookKey))
                .thenReturn(successOpenOrderBook)

            val response = remoteOrderBooksRepositoryImpl.getListOrderBook(mockSelectedBookKey)

            assertThat(response).isNotNull()
            assertThat(response).isInstanceOf(BitsoApiResponseWrap.Success::class.java)

            response.apply {
                if (this is BitsoApiResponseWrap.Success) {
                    val unwrapped = this.response
                    assertThat(unwrapped.success).isTrue()
                    assertThat(unwrapped.payload).isNotNull()
                    assertThat(unwrapped.payload).isInstanceOf(ListOrderBookResponse::class.java)
                    assertThat(unwrapped.payload!!.asks).isNotNull()
                    assertThat(unwrapped.payload!!.asks).isNotEmpty()
                    assertThat(unwrapped.payload!!.bids).isNotNull()
                    assertThat(unwrapped.payload!!.bids).isNotEmpty()
                    assertThat(
                        unwrapped.payload!!.asks!!.first().book ==
                            successOpenOrderBook.payload!!.asks!!.first().book,
                    ).isTrue()
                    assertThat(
                        unwrapped.payload!!.bids!!.first().book ==
                            successOpenOrderBook.payload!!.bids!!.first().book,
                    ).isTrue()
                }
            }
        }
    }
}
