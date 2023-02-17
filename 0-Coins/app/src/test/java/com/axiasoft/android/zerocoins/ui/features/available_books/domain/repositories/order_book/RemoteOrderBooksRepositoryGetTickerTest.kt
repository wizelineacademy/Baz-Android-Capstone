package com.axiasoft.android.zerocoins.ui.features.available_books.domain.repositories.order_book

import com.axiasoft.android.zerocoins.network.bitso.models.BitsoBaseResponse
import com.axiasoft.android.zerocoins.network.bitso.wrappers.BitsoApiResponseWrap
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.apis.BitsoOrderBooksApi
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.ticker.response.TickerResponse
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class RemoteOrderBooksRepositoryGetTickerTest {

    private lateinit var remoteOrderBooksRepositoryImpl: RemoteOrderBooksRepositoryImpl

    private val mockSelectedBookKey = "mana_btc"

    private val successMockTickerResponse = BitsoBaseResponse(
        success = true,
        payload = TickerResponse(
            book = "mana_btc",
            high = "70000",
            low = "1000",
            last = "69999"
        )
    )

    @Mock
    private lateinit var bitsoOrderBooksApi: BitsoOrderBooksApi

    @Before
    fun setup() {
        bitsoOrderBooksApi = mock()
        remoteOrderBooksRepositoryImpl = RemoteOrderBooksRepositoryImpl(bitsoOrderBooksApi)
    }

    @Test
    fun `test remote repository getTickerFromApi success`() {
        runBlocking {
            whenever(bitsoOrderBooksApi.getTicketsApi(mockSelectedBookKey)).thenReturn(
                successMockTickerResponse
            )
            val response = remoteOrderBooksRepositoryImpl.getTickerFromApi(mockSelectedBookKey)
            assertThat(response).isNotNull()
            assertThat(response).isInstanceOf(BitsoApiResponseWrap.Success::class.java)
            response.apply {
                if (this is BitsoApiResponseWrap.Success) {
                    val unwrapped = this.response
                    assertThat(unwrapped.success).isTrue()
                    assertThat(unwrapped.payload).isNotNull()
                    assertThat(unwrapped.payload).isInstanceOf(TickerResponse::class.java)
                    assertThat(
                        unwrapped.payload!!.book == successMockTickerResponse.payload!!.book
                    ).isTrue()
                }
                //assertThat(response).isTrue()
                //assertTrue(this. == true)
            }
        }
    }
}