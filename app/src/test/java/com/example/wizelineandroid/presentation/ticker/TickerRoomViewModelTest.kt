package com.example.wizelineandroid.presentation.ticker

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.wizelineandroid.data.remote.model.GetTicker
import com.example.wizelineandroid.data.remote.model.GetTickers
import com.example.wizelineandroid.repository.ticker.TickerRepo
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TickerRoomViewModelTest{

    @RelaxedMockK
    private lateinit var tickerRoom: TickerRepo

    @get:Rule
    var instantExcecutorRule = InstantTaskExecutorRule()


    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
    }

    @Test
    fun `when info api is empty`() = runBlocking {

        val book = "btc_mxn"
        //given
        coEvery { tickerRoom.getTickerBooks(book) } returns GetTickers(
            GetTicker(  "", "", "")
        )

        //when
        tickerRoom.getTickerBooks(book)

        //then
        coVerify(exactly = 1) { tickerRoom.getTickerBooks(book) }
    }

    @Test
    fun `when get info from ticker`() = runBlocking {
        val book = "btc_mxn"
        val infoBook = GetTickers(GetTicker("10", "5", "1"))
        //given
        coEvery { tickerRoom.getTickerBooks(book) } returns infoBook

        //when
        val response = tickerRoom.getTickerBooks(book)

        //then
        coVerify(exactly = 1) { tickerRoom.getTickerBooks(book) }
        assert(infoBook == response)
    }



}