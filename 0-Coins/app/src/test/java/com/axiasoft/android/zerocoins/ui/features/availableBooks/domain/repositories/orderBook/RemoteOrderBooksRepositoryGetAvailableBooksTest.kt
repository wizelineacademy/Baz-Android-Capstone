package com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.repositories.orderBook

import com.axiasoft.android.zerocoins.network.bitso.models.BitsoBaseResponse
import com.axiasoft.android.zerocoins.network.bitso.wrappers.BitsoApiResponseWrap
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.apis.BitsoOrderBooksApi
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.exchangeOrderBook.response.ExchangeOrderBookResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class RemoteOrderBooksRepositoryGetAvailableBooksTest {

    private lateinit var remoteOrderBooksRepositoryImpl: RemoteOrderBooksRepositoryImpl

    @Mock
    private lateinit var bitsoOrderBooksApi: BitsoOrderBooksApi

    private val successMockBookResponse = BitsoBaseResponse(
        success = true,
        payload = arrayListOf(
            ExchangeOrderBookResponse(book = "btc_mxn", minimumAmount = "0.00000060000", maximumAmount = "600", minimumPrice = "20000", maximumPrice = "7000000", minimumValue = "10.00", maximumValue = "200000000"),
            ExchangeOrderBookResponse(book = "eth_btc", minimumAmount = "0.00000800000", maximumAmount = "8000.00", minimumPrice = "0.004", maximumPrice = "1.00", minimumValue = "0.00003000", maximumValue = "600.00"),
            ExchangeOrderBookResponse(book = "eth_mxn", minimumAmount = "0.00000800000", maximumAmount = "8000.00", minimumPrice = "1000", maximumPrice = "500000.00", minimumValue = "10.00000000", maximumValue = "200000000.00"),
            ExchangeOrderBookResponse(book = "xrp_btc", minimumAmount = "0.03000000000", maximumAmount = "30000000.00", minimumPrice = "0.000001", maximumPrice = "0.0004", minimumValue = "0.00003000", maximumValue = "600.00"),
            ExchangeOrderBookResponse(book = "xrp_mxn", minimumAmount = "0.0300000", maximumAmount = "30000000", minimumPrice = "0.4", maximumPrice = "100", minimumValue = "10.00", maximumValue = "200000000"),
            ExchangeOrderBookResponse(book = "ltc_btc", minimumAmount = "0.0001", maximumAmount = "100000", minimumPrice = "0.0002", maximumPrice = "0.09", minimumValue = "0.00003", maximumValue = "600"),
            ExchangeOrderBookResponse(book = "ltc_mxn", minimumAmount = "0.0001", maximumAmount = "100000", minimumPrice = "70", maximumPrice = "30000", minimumValue = "10.00000", maximumValue = "200000000"),
            ExchangeOrderBookResponse(book = "bch_btc", minimumAmount = "0.0001", maximumAmount = "100000", minimumPrice = "0.0003", maximumPrice = "0.1", minimumValue = "0.00003", maximumValue = "600"),
            ExchangeOrderBookResponse(book = "bch_mxn", minimumAmount = "0.0001", maximumAmount = "100000", minimumPrice = "100", maximumPrice = "40000", minimumValue = "10.00000", maximumValue = "200000000"),
            ExchangeOrderBookResponse(book = "tusd_btc", minimumAmount = "0.0100000", maximumAmount = "10000000", minimumPrice = "0.000003", maximumPrice = "0.001", minimumValue = "0.00003", maximumValue = "600"),
            ExchangeOrderBookResponse(book = "tusd_mxn", minimumAmount = "0.0100000", maximumAmount = "10000000", minimumPrice = "1", maximumPrice = "400", minimumValue = "10.00000", maximumValue = "200000000"),
            ExchangeOrderBookResponse(book = "mana_btc", minimumAmount = "0.03", maximumAmount = "30000000", minimumPrice = "0.0000009", maximumPrice = "0.0004", minimumValue = "0.00003", maximumValue = "600"),
            ExchangeOrderBookResponse(book = "mana_mxn", minimumAmount = "0.03", maximumAmount = "30000000", minimumPrice = "0.3", maximumPrice = "100", minimumValue = "10.00000", maximumValue = "200000000"),
            ExchangeOrderBookResponse(book = "bat_btc", minimumAmount = "0.06", maximumAmount = "60000000", minimumPrice = "0.0000005", maximumPrice = "0.0002", minimumValue = "0.00003", maximumValue = "600"),
            ExchangeOrderBookResponse(book = "bat_mxn", minimumAmount = "0.06", maximumAmount = "60000000", minimumPrice = "0.2", maximumPrice = "70", minimumValue = "10.00000", maximumValue = "200000000"),
            ExchangeOrderBookResponse(book = "btc_ars", minimumAmount = "0.0000006", maximumAmount = "600", minimumPrice = "300000", maximumPrice = "100000000", minimumValue = "200", maximumValue = "3000000000"),
            ExchangeOrderBookResponse(book = "btc_dai", minimumAmount = "0.0000006", maximumAmount = "600", minimumPrice = "800", maximumPrice = "300000", minimumValue = "0.50000", maximumValue = "10000000"),
            ExchangeOrderBookResponse(book = "dai_mxn", minimumAmount = "0.0100000", maximumAmount = "10000000", minimumPrice = "1", maximumPrice = "400", minimumValue = "10.00000", maximumValue = "200000000"),
            ExchangeOrderBookResponse(book = "btc_usd", minimumAmount = "0.0000006", maximumAmount = "600", minimumPrice = "800", maximumPrice = "300000", minimumValue = "0.50000", maximumValue = "10000000"),
            ExchangeOrderBookResponse(book = "xrp_usd", minimumAmount = "0.03", maximumAmount = "30000000", minimumPrice = "0.02", maximumPrice = "7", minimumValue = "0.50000", maximumValue = "10000000"),
            ExchangeOrderBookResponse(book = "eth_usd", minimumAmount = "0.000008", maximumAmount = "8000", minimumPrice = "60", maximumPrice = "20000", minimumValue = "0.50000", maximumValue = "10000000"),
            ExchangeOrderBookResponse(book = "dai_ars", minimumAmount = "0.0100000", maximumAmount = "10000000", minimumPrice = "20", maximumPrice = "7000", minimumValue = "200", maximumValue = "3000000000"),
            ExchangeOrderBookResponse(book = "btc_brl", minimumAmount = "0.0000006", maximumAmount = "600", minimumPrice = "4000", maximumPrice = "2000000", minimumValue = "3.00000", maximumValue = "50000000"),
            ExchangeOrderBookResponse(book = "eth_ars", minimumAmount = "0.000008", maximumAmount = "8000", minimumPrice = "20000", maximumPrice = "8000000", minimumValue = "200", maximumValue = "3000000000"),
        ),
    )

    @Before
    fun setup() {
        bitsoOrderBooksApi = mock()
        remoteOrderBooksRepositoryImpl = RemoteOrderBooksRepositoryImpl(bitsoOrderBooksApi)
    }

    @Test
    fun `test remote repository getBooksFromApi success`() {
        // given
        runBlocking {
            whenever(bitsoOrderBooksApi.getBooksFromApi()).thenReturn(successMockBookResponse)
            // when
            val response = remoteOrderBooksRepositoryImpl.getBooksFromApi()
            // then
            assertTrue(response is BitsoApiResponseWrap.Success)
            response.apply {
                assertNotNull(response)
                // TODO test is over response mock (before) or response (after)?
                assertTrue(successMockBookResponse.success!!)
                assertNotNull(successMockBookResponse.payload)
                assertTrue(successMockBookResponse.payload!!.isNotEmpty())
                assertEquals(successMockBookResponse.payload!!.first().book, "btc_mxn")
            }
        }
    }
}
