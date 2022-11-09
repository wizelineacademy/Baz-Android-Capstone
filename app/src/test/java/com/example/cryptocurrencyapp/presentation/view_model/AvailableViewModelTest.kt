package com.example.cryptocurrencyapp.presentation.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cryptocurrencyapp.domain.entity.WCCOrdeRDTO
import com.example.cryptocurrencyapp.domain.entity.WCCryptoBookDTO
import com.example.cryptocurrencyapp.domain.use_case.WCCAvailableUseCase
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

class AvailableViewModelTest{
     private val availableUseCase: WCCAvailableUseCase = mockk()
    private lateinit var availableViewModel: AvailableViewModel

    @get:Rule
    val rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        availableViewModel = AvailableViewModel(availableUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }
    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when view model is created at the first time and obtain a default format value from order method` () = runTest {
        //Given
        val mockData: List<WCCryptoBookDTO> = listOf(
            WCCryptoBookDTO(maxPrice = "12", minPrice = "14")
        )

        coEvery { availableUseCase.coin() } returns flow { emit(Resource.Success(mockData)) }

        //When
        availableViewModel.getAvailableBook()

        //Then
        assert(availableViewModel.coins.value == mockData)
    }



 }