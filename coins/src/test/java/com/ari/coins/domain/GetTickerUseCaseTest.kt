package com.ari.coins.domain

import com.ari.coins.data.models.ResultData
import com.ari.coins.data.models.TickerData
import com.ari.coins.data.repository.CoinsRepository
import com.ari.coins.domain.domainModels.ResultDomain
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetTickerUseCaseTest {

    private lateinit var getTickerUseCase: GetTickerUseCase
    private val book = "eth_mxn"
    private val ticker = TickerData("", "", "", "", "", "", "", "", "")

    @RelaxedMockK
    private lateinit var coinsRepository: CoinsRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getTickerUseCase = GetTickerUseCase(coinsRepository)
    }

    @Test
    fun `when server returns success response, get ResultDomain_Success`() = runBlocking {
        // Given
        coEvery { coinsRepository.getTicker(any()) } returns ResultData.Success(ticker)

        // When
        val result = getTickerUseCase(book)

        // Then
        assert(result is ResultDomain.Success)
    }

    @Test
    fun `when server returns success response, save ticker in DB`() = runBlocking {
        // Given
        coEvery { coinsRepository.getTicker(any()) } returns ResultData.Success(ticker)

        // When
        getTickerUseCase(book)

        // Then
        coVerify(exactly = 1) { coinsRepository.deleteTickerFromDB(any()) }
        coVerify(exactly = 1) { coinsRepository.insertTickerToDB(any()) }
    }

    @Test
    fun `when server returns failure response, check local data`() = runBlocking {
        // Given
        coEvery { coinsRepository.getTicker(any()) } returns ResultData.Error("", -1)
        coEvery { coinsRepository.getTickerFromDB(any()) } returns ticker

        // When
        getTickerUseCase(book)

        // Then
        coVerify(exactly = 1) { coinsRepository.getTickerFromDB(any()) }
    }

    @Test
    fun `when server returns failure response and has no local data, get ResultDomain_Error`() =
        runBlocking {
            // Given
            coEvery { coinsRepository.getTicker(any()) } returns ResultData.Error("", -1)
            coEvery { coinsRepository.getTickerFromDB(any()) } returns null

            // When
            val result = getTickerUseCase(book)

            // Then
            assert(result is ResultDomain.Error)
        }

    @Test
    fun `when the server returns a failure response and there is local data, get data from local DB`() =
        runBlocking {
            // Given
            coEvery { coinsRepository.getTicker(any()) } returns ResultData.Error("", -1)
            coEvery { coinsRepository.getTickerFromDB(any()) } returns ticker

            // When
            val result = getTickerUseCase(book)

            // Then
            coVerify(exactly = 1) { coinsRepository.getTickerFromDB(any()) }
            assert(result is ResultDomain.Success)
        }
}
