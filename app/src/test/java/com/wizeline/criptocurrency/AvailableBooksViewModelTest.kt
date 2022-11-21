package com.wizeline.criptocurrency

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wizeline.criptocurrency.common.adapters.RequestState
import com.wizeline.criptocurrency.domain.model.AvailableBook
import com.wizeline.criptocurrency.domain.model.use_case.AvailableBooksUseCase
import com.wizeline.criptocurrency.ui.AvailableBooksViewModel
import io.mockk.coEvery
import io.mockk.coVerify
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

class AvailableBooksViewModelTest {

    private lateinit var systemUnderTest: AvailableBooksViewModel
    private val availableBooksUseCase: AvailableBooksUseCase = mockk()

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        systemUnderTest = AvailableBooksViewModel(
            availableBooksUseCase = availableBooksUseCase
        )
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get Available Books Success Response with not empty list`() = runTest {

        // Given
        coEvery { availableBooksUseCase.invoke() } returns flow {
            RequestState.Success<List<AvailableBook>>(listAvailableOrderBooks)
        }

        // When
        systemUnderTest.getAvailableBooks()

        // Then
        systemUnderTest.availableOrderBookList.value?.isNullOrEmpty()?.let {
            assert(
                !it
            )
        }
    }

    @Test
    fun `function getAvailableBooksUseCaseinvoke() is called once`() = runTest {
        // Given
        coEvery { availableBooksUseCase.invoke() } returns flow {
            RequestState.Success<List<AvailableBook>>(emptyList())
        }

        // When
        systemUnderTest.getAvailableBooks()

        // Then
        coVerify(exactly = 1) {
            availableBooksUseCase.invoke()
        }
    }
}
