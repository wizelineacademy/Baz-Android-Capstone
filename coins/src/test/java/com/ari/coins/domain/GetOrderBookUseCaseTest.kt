package com.ari.coins.domain

import com.ari.coins.data.models.OrderBookData
import com.ari.coins.data.models.ResultData
import com.ari.coins.data.repository.CoinsRepository
import com.ari.coins.domain.domainModels.ResultDomain
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetOrderBookUseCaseTest {

    private lateinit var getOrderBookUseCase: GetOrderBookUseCase

    @RelaxedMockK
    private lateinit var coinsRepository: CoinsRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getOrderBookUseCase = GetOrderBookUseCase(coinsRepository)
    }

    @Test
    fun `when server returns success response, get ResultDomain_Success`() = runBlocking {
        // Given
        val book = "eth_mxn"
        val orderBookData = OrderBookData(emptyList(), emptyList(), "", "")
        coEvery { coinsRepository.getOrderBook(book) } returns ResultData.Success(orderBookData)

        // When
        val result = getOrderBookUseCase(book)

        // Then
        assert(result is ResultDomain.Success)
    }

    @Test
    fun `when server returns success response, save orderBook in DB`() = runBlocking {
        // Given
        val book = "eth_mxn"
        val orderBookData = OrderBookData(emptyList(), emptyList(), "", "")
        coEvery { coinsRepository.getOrderBook(book) } returns ResultData.Success(orderBookData)

        // When
        getOrderBookUseCase(book)

        // Then
        coVerify(exactly = 1) { coinsRepository.deleteOrderBookFromDB(any()) }
        coVerify(exactly = 1) { coinsRepository.insertOrderBookToDB(any(), any()) }
    }

    @Test
    fun `when server returns failure response, check local data`() = runBlocking {
        // Given
        val book = "eth_mxn"
        val orderBookData = OrderBookData(emptyList(), emptyList(), "", "")
        coEvery { coinsRepository.getOrderBook(book) } returns ResultData.Error("", -1)
        coEvery { coinsRepository.getOrderBookFromDB(book) } returns orderBookData

        // When
        getOrderBookUseCase(book)

        // Then
        coVerify(exactly = 1) { coinsRepository.getOrderBookFromDB(any()) }
    }

    @Test
    fun `when server returns failure response and has no local data, get ResultDomain_Error`() =
        runBlocking {
            // Given
            coEvery { coinsRepository.getOrderBook(any()) } returns ResultData.Error("", -1)
            coEvery { coinsRepository.getOrderBookFromDB(any()) } returns null

            // When
            val result = getOrderBookUseCase("eth_mxn")

            // Then
            assert(result is ResultDomain.Error)
        }

    @Test
    fun `when the server returns a failure response and there is local data, get data from local DB`() =
        runBlocking {
            // Given
            val orderBookData = OrderBookData(emptyList(), emptyList(), "", "")
            coEvery { coinsRepository.getOrderBook(any()) } returns ResultData.Error("", -1)
            coEvery { coinsRepository.getOrderBookFromDB(any()) } returns orderBookData

            // When
            val result = getOrderBookUseCase("eth_mxn")

            // Then
            coVerify(exactly = 1) { coinsRepository.getOrderBookFromDB(any()) }
            assert(result is ResultDomain.Success)
        }
}
