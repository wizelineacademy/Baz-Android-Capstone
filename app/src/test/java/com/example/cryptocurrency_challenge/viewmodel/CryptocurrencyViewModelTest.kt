package com.example.cryptocurrency_challenge.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cryptocurrency_challenge.data.model.Payload
import com.example.cryptocurrency_challenge.domain.AvailableBooksUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CryptocurrencyViewModelTest{

    @RelaxedMockK
    private lateinit var availableBooksUseCase: AvailableBooksUseCase

    private lateinit var vmAvailableBooks: CryptocurrencyViewModel

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        vmAvailableBooks = CryptocurrencyViewModel(availableBooksUseCase = availableBooksUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `Cuando VM recibe correctamente la informacion del api el loader se setea en false` () =  runTest {

        //Given
        val myTest = listOf(
            Payload("btc_mxn"),
            Payload("xrp_mxn")
        )

        coEvery { availableBooksUseCase() } returns  myTest

        //When
        vmAvailableBooks.getAvailableBooks()

        //Then
        assert(vmAvailableBooks.isLoading.value == false)

    }

    @Test
    fun `Cuando VM recibe correctamente la informacion del api se setea vmAvailableBooks` () =  runTest {

        //Given
        val myTest = listOf(
            Payload("btc_mxn"),
            Payload("xrp_mxn")
        )

        coEvery { availableBooksUseCase.invoke() } returns  myTest

        //When
        vmAvailableBooks.getAvailableBooks()

        //Then
        assert(vmAvailableBooks.availableBookModel.value!!.payLoadList == myTest)

    }

}