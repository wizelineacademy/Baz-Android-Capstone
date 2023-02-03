package com.example.cryptocurrencyapp.presentation.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cryptocurrencyapp.domain.entity.WCCOrdeRDTO
import com.example.cryptocurrencyapp.domain.entity.WCCTickerDTO
import com.example.cryptocurrencyapp.domain.use_case.DetailUseCase
import com.example.cryptocurrencyapp.utils.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class DetailViewModelTest{
    private var getDetailUseCase: DetailUseCase = mockk()
    private lateinit var detailCryptoViewModel: DetailViewModel

    @get:Rule
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        detailCryptoViewModel = DetailViewModel(getDetailUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when view model is created at the first time and obtain a default format value from order method` () = runTest {
        //Given
        val idBookMock = "btc_mxn"
        val mockData = WCCOrdeRDTO()
        coEvery { getDetailUseCase.order(idBookMock) } returns flow { emit(Resource.Success(mockData)) }

        //When
        detailCryptoViewModel.getOrderBook(idBookMock)

        //Then
        assert(detailCryptoViewModel.resumeOrder.value == mockData)
    }

    @Test
    fun `when the viewmodel is create at the first time and obtain a default format value from ticker method` () = runTest {
        //Given
        val idBookMock = "btc_mx"
        val mockData = WCCTickerDTO()
        coEvery { getDetailUseCase.ticker(idBookMock) } returns flow { emit(Resource.Success(mockData))}

        //When
        detailCryptoViewModel.getTicker(idBookMock)

        //Then
        assert(detailCryptoViewModel.resumeTicker.value == mockData)
    }

    @Test
    fun `when the viewmodel is create at the first time and obtain a correct format value from getDetailTicker method`() = runTest {
        //Given
        val idBookMock = "btc_mxn"
        val mockData = WCCTickerDTO( low = "1", high = "34")
        coEvery { getDetailUseCase.ticker(idBookMock) } returns flow { emit(Resource.Success(mockData)) }

        //When
        detailCryptoViewModel.getTicker(idBookMock)

        //Then
        assert(detailCryptoViewModel.resumeTicker.value == mockData)
    }


}