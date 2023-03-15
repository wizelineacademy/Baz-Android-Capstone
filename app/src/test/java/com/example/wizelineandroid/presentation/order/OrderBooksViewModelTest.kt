package com.example.wizelineandroid.presentation.order

import com.example.wizelineandroid.data.remote.model.Ask
import com.example.wizelineandroid.data.remote.model.AsksBids
import com.example.wizelineandroid.data.remote.model.Bids
import com.example.wizelineandroid.data.remote.model.OrderBook
import com.example.wizelineandroid.repository.order.OrderBookRepo
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class OrderBooksViewModelTest{

    @RelaxedMockK
    private lateinit var repository: OrderBookRepo

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when get info api empty`() = runBlocking {

        val book = "btc_mxn"
        //given
        coEvery { repository.getOrderBooks(book) } returns OrderBook(
            AsksBids(
                emptyList(),
                emptyList(),
                ""
            )
        )

        //when
        repository.getOrderBooks(book)

        //then
        coVerify(exactly = 1) { repository.getOrderBooks(book) }
    }

    @Test
    fun `when get info ask and bids`() = runBlocking {
        val book = "btc_mxn"
        val asks = listOf(
            Ask("10", "15","20"),
            Ask("15", "25","35")
        )
        val bids = listOf(
            Bids("20", "35","50"),
            Bids("20", "35","50")
        )

        val infoBook = OrderBook(
            AsksBids(
                asks,
                bids,
                "Sab 19 de Noviembre"
            )
        )
        //given
        coEvery { repository.getOrderBooks(book) } returns infoBook

        //when
        val response = repository.getOrderBooks(book)

        //then
        coVerify(exactly = 1) { repository.getOrderBooks(book) }
        assert(infoBook == response)
    }
}
