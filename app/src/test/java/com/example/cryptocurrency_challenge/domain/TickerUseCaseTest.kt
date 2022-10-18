package com.example.cryptocurrency_challenge.domain

import com.example.cryptocurrency_challenge.data.model.Payload_Ticker
import com.example.cryptocurrency_challenge.repository.TickerRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class TickerUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: TickerRepository

    lateinit var tickerUseCase: TickerUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        tickerUseCase = TickerUseCase(repository)
    }

    @Test
    fun `Cuando La Api Ticker Responde Exitosamente`() = runBlocking {

        //Given
        val myListTest = Payload_Ticker("btc_mxn", "5","4","3")

        coEvery { repository.get_Ticker("btc_mxn")} returns myListTest

        //When
        val responseBook = tickerUseCase("btc_mxn").book.isNotEmpty()

        //Then
        assert(responseBook == myListTest.book.isNotEmpty())

    }

}

