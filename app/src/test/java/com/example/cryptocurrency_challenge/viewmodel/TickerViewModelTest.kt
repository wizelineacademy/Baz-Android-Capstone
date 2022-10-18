package com.example.cryptocurrency_challenge.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cryptocurrency_challenge.data.model.Payload_Ticker
import com.example.cryptocurrency_challenge.domain.TickerUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TickerViewModelTest{

    @RelaxedMockK
    private lateinit var tickerUseCase: TickerUseCase

    private lateinit var vmTicker: TickerViewModel

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        vmTicker = TickerViewModel(tickerUseCase = tickerUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `Cuando vm recibe correctamente la informacion del api el loader se setea en false` () =  runTest {

        //Given
        val myTickerTest = Payload_Ticker("btc_mxn", "5","4","3")

        coEvery { tickerUseCase("btc_mxn") } returns  myTickerTest

        //When
        vmTicker.geTicker("btc_mxn")

        //Then
        assert(vmTicker.isLoading.value == false)

    }

    @Test
    fun `Cuando vm recibe correctamente la informacion del api se setea payLoadTicker` () =  runTest {

        //Given
        val myTickerTest = Payload_Ticker("btc_mxn", "5","4","3")

        coEvery { tickerUseCase("btc_mxn") } returns  myTickerTest

        //When
        vmTicker.geTicker("btc_mxn")

        //Then
        assert(vmTicker.payLoadTicker.value!!.payLoadTicker == myTickerTest)

    }

}