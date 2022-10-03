package com.ari.coins.ui.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ari.coins.domain.GetAvailableBooksUseCase
import com.ari.coins.domain.GetCoinUrlImageUseCase
import com.ari.coins.domain.GetOrderBookUseCase
import com.ari.coins.domain.GetTickerUseCase
import com.ari.coins.domain.domainModels.OrderBookDomain
import com.ari.coins.domain.domainModels.ResultDomain
import com.ari.coins.domain.domainModels.TickerDomain
import com.ari.coins.ui.uiModels.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CoinsViewModelTest {

    private lateinit var coinsViewModel: CoinsViewModel

    @RelaxedMockK
    private lateinit var getAvailableBooksUseCase: GetAvailableBooksUseCase

    @RelaxedMockK
    private lateinit var getTickerUseCase: GetTickerUseCase

    @RelaxedMockK
    private lateinit var getOrderBookUseCase: GetOrderBookUseCase

    @RelaxedMockK
    private lateinit var getCoinUrlImageUseCase: GetCoinUrlImageUseCase

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        coinsViewModel = CoinsViewModel(
            getAvailableBooksUseCase,
            getTickerUseCase,
            getOrderBookUseCase,
            getCoinUrlImageUseCase,
            UnconfinedTestDispatcher()
        )
        Dispatchers.setMain(Dispatchers.IO)
    }

    @After
    fun onAfter() = Dispatchers.resetMain()

    @Test
    fun `when call to clearCoinDetailView, clean _ticker and _orderBook`() = runTest {
        // Given

        // When
        coinsViewModel.clearCoinDetailView()

        // Then
        assert(coinsViewModel.ticker.value is Result.Empty)
        assert(coinsViewModel.orderBook.value is Result.Empty)
    }

    @Test
    fun `when call to getAvailableBooks, update availableBooks observable with Error`() = runTest {
        // Given
        coEvery { getAvailableBooksUseCase(null) } returns ResultDomain.Error("", -1)

        // When
        coinsViewModel.getAvailableBooks()

        // Then
        assert(coinsViewModel.availableBooks.value is Result.Error)
    }

    @Test
    fun `when call to getAvailableBooks, update availableBooks observable with Success`() = runTest {
        // Given
        coEvery { getAvailableBooksUseCase(null) } returns ResultDomain.Success(emptyList())

        // When
        coinsViewModel.getAvailableBooks()

        // Then
        assert(coinsViewModel.availableBooks.value is Result.Success)
    }

    @Test
    fun `when call to getTicker, update ticker observable with Error`() = runTest {
        // Given
        coEvery { getTickerUseCase(any()) } returns ResultDomain.Error("", -1)

        // When
        coinsViewModel.getTicker("eth_mxn")

        // Then
        assert(coinsViewModel.ticker.value is Result.Error)
    }

    @Test
    fun `when call to getTicker, update ticker observable with Success`() = runTest {
        // Given
        val ticker = TickerDomain("", "", "", "", "", "", "", "", "")
        coEvery { getTickerUseCase(any()) } returns ResultDomain.Success(ticker)

        // When
        coinsViewModel.getTicker("eth_mxn")

        // Then
        assert(coinsViewModel.ticker.value is Result.Success)
    }

    @Test
    fun `when call to getOrderBook, update orderBook observable with Error`() = runTest {
        // Given
        coEvery { getOrderBookUseCase(any()) } returns ResultDomain.Error("", -1)

        // When
        coinsViewModel.getOrderBook("eth_mxn")

        // Then
        assert(coinsViewModel.orderBook.value is Result.Error)
    }

    @Test
    fun `when call to getOrderBook, update orderBook observable with Success`() = runTest {
        // Given
        val orderBook = OrderBookDomain(emptyList(), emptyList(), "", "")
        coEvery { getOrderBookUseCase(any()) } returns ResultDomain.Success(orderBook)

        // When
        coinsViewModel.getOrderBook("eth_mxn")

        // Then
        assert(coinsViewModel.orderBook.value is Result.Success)
    }
}