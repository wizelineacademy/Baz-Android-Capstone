package com.ari.coins.domain

import com.ari.coins.data.models.AvailableBookData
import com.ari.coins.data.models.FeesData
import com.ari.coins.data.models.FlatRateData
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

class GetAvailableBooksUseCaseTest {

    private lateinit var getAvailableBooksUseCase: GetAvailableBooksUseCase

    @RelaxedMockK
    private lateinit var coinsRepository: CoinsRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getAvailableBooksUseCase = GetAvailableBooksUseCase(coinsRepository)
    }

    @Test
    fun `when server returns success response, get ResultDomain_Success`() = runBlocking {
        // Given
        coEvery { coinsRepository.getAvailableBooks() } returns ResultData.Success(emptyList())

        // When
        val result = getAvailableBooksUseCase(null)

        // Then
        assert(result is ResultDomain.Success)
    }

    @Test
    fun `when server returns success response, save availableBooks in DB`() = runBlocking {
        // Given
        coEvery { coinsRepository.getAvailableBooks() } returns ResultData.Success(emptyList())

        // When
        getAvailableBooksUseCase(null)

        // Then
        coVerify(exactly = 1) { coinsRepository.clearAvailableBookTableFormDB() }
        coVerify(exactly = 1) { coinsRepository.insertAvailableBooksToDB(any()) }
    }

    @Test
    fun `when server returns failure response, check local data`() = runBlocking {
        // Given
        coEvery { coinsRepository.getAvailableBooks() } returns ResultData.Error("", -1)
        coEvery { coinsRepository.getAvailableBooksFromDB() } returns emptyList()

        // When
        getAvailableBooksUseCase(null)

        // Then
        coVerify(exactly = 1) { coinsRepository.getAvailableBooksFromDB() }
    }

    @Test
    fun `when server returns failure response and has no local data, get ResultDomain_Error`() =
        runBlocking {
            // Given
            coEvery { coinsRepository.getAvailableBooks() } returns ResultData.Error("", -1)
            coEvery { coinsRepository.getAvailableBooksFromDB() } returns emptyList()

            // When
            val result = getAvailableBooksUseCase(null)

            // Then
            assert(result is ResultDomain.Error)
        }

    @Test
    fun `when the server returns a failure response and there is local data, get data from local DB`() =
        runBlocking {
            // Given
            coEvery { coinsRepository.getAvailableBooks() } returns ResultData.Error("", -1)
            coEvery { coinsRepository.getAvailableBooksFromDB() } returns listOf(
                AvailableBookData(
                    "",
                    "",
                    FeesData(FlatRateData("", ""), emptyList()),
                    "", "", "",
                    "", "", "", ""
                )
            )

            // When
            val result = getAvailableBooksUseCase(null)

            // Then
            coVerify(exactly = 1) { coinsRepository.getAvailableBooksFromDB() }
            assert(result is ResultDomain.Success)
        }
}
