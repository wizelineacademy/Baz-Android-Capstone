package com.capstone.capstonecoins.data.repository

import com.capstone.capstonecoins.data.models.ticker.tickerquery.Payload
import com.capstone.capstonecoins.data.models.ticker.tickerquery.RollingAverageChange
import com.capstone.capstonecoins.data.models.ticker.tickerquery.TickerWithQuery
import com.capstone.capstonecoins.domain.api.ApiService
import com.capstone.capstonecoins.domain.api.BooksDao
import com.capstone.capstonecoins.domain.api.DetailCoinRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DetailCoinRepositoryImplTest {


    lateinit var api: ApiService
    lateinit var dao: BooksDao
    lateinit var systemUnderTest: DetailCoinRepository


    @Before
    fun setup() {
        api = mockk()
        dao = mockk()
        systemUnderTest = DetailCoinRepositoryImpl(api, dao)
    }

    @Test
    fun getOneBookSuccessfull() = runBlocking {
        //Given
        val typeCoin = "btc"
        coEvery {
            api.getTicker(typeCoin)
        } returns mockTickerWithQueryResult
        //When
        val result = systemUnderTest.getDetailCoin("btc")

        //Then
        Assert.assertEquals("btc_mxn", result.book)
    }

    val mockTickerWithQueryResult = TickerWithQuery(
        Payload(
            "ask",
            "bid",
            "btc_mxn",
            "wds",
            "",
            "0.324",
            "0.43",
            "",
            RollingAverageChange(""),
            "",
            ""
        ), true
    )
}