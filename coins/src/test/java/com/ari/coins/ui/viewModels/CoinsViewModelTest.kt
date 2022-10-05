package com.ari.coins.ui.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ari.coins.domain.GetAvailableBooksUseCase
import com.ari.coins.domain.GetCoinUrlImageUseCase
import com.ari.coins.domain.GetOrderBookUseCase
import com.ari.coins.domain.GetTickerUseCase
import com.ari.coins.domain.domainModels.OrderBookDomain
import com.ari.coins.domain.domainModels.ResultDomain
import com.ari.coins.domain.domainModels.TickerDomain
import com.ari.coins.ui.uiModels.AvailableBook
import com.ari.coins.ui.uiModels.Error
import com.ari.coins.ui.uiModels.OrderBook
import com.ari.coins.ui.uiModels.Ticker
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
    fun `when call to clearCoinDetailView, clean ticker and orderBook data (success and error)`() = runTest {
        // Given

        // When
        coinsViewModel.clearCoinDetailView()

        // Then
        assert(coinsViewModel.successTicker.value == null)
        assert(coinsViewModel.errorTicker.value == null)
        assert(coinsViewModel.successOrderBook.value == null)
        assert(coinsViewModel.errorAvailableBooks.value == null)
        assert(coinsViewModel.errorAvailableBooks.value == null)
    }

    @Test
    fun `when call to getAvailableBooks and returned failure, update errorAvailableBooks observable with Error`() =
        runTest {
            // Given
            coEvery { getAvailableBooksUseCase(null) } returns ResultDomain.Error("", -1)

            // When
            coinsViewModel.getAvailableBooks()

            // Then
            assert(coinsViewModel.errorAvailableBooks.value != null)
            assert(coinsViewModel.errorAvailableBooks.value is Error)
        }

    @Test
    fun `when call to getAvailableBooks, update successAvailableBooks observable with a list`() =
        runTest {
            // Given
            coEvery { getAvailableBooksUseCase(null) } returns ResultDomain.Success(emptyList())

            // When
            coinsViewModel.getAvailableBooks()

            // Then
            assert(coinsViewModel.successAvailableBooks.value != null)
            assert(coinsViewModel.successAvailableBooks.value is List<AvailableBook>)
        }

    @Test
    fun `when call to getTicker, update ticker errorTicker with Error`() = runTest {
        // Given
        coEvery { getTickerUseCase(any()) } returns ResultDomain.Error("", -1)

        // When
        coinsViewModel.getTicker("eth_mxn")

        // Then
        assert(coinsViewModel.errorTicker.value != null)
        assert(coinsViewModel.errorTicker.value is Error)
    }

    @Test
    fun `when call to getTicker, update successTicker observable with Success`() = runTest {
        // Given
        val ticker = TickerDomain("", "", "", "", "", "", "", "", "")
        coEvery { getTickerUseCase(any()) } returns ResultDomain.Success(ticker)

        // When
        coinsViewModel.getTicker("eth_mxn")

        // Then
        assert(coinsViewModel.successTicker.value != null)
        assert(coinsViewModel.successTicker.value is Ticker)
    }

    @Test
    fun `when call to getOrderBook and return an error, update errorOrderBook observable with Error`() = runTest {
        // Given
        coEvery { getOrderBookUseCase(any()) } returns ResultDomain.Error("", -1)

        // When
        coinsViewModel.getOrderBook("eth_mxn")

        // Then
        assert(coinsViewModel.errorOrderBook.value != null)
        assert(coinsViewModel.errorOrderBook.value is Error)
    }

    @Test
    fun `when call to getOrderBook and return success, update successOrderBook observable with Success`() = runTest {
        // Given
        val orderBook = OrderBookDomain(emptyList(), emptyList(), "", "")
        coEvery { getOrderBookUseCase(any()) } returns ResultDomain.Success(orderBook)

        // When
        coinsViewModel.getOrderBook("eth_mxn")

        // Then
        assert(coinsViewModel.successOrderBook.value != null)
        assert(coinsViewModel.successOrderBook.value is OrderBook)
    }
}
