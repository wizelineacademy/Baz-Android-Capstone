package com.axiasoft.android.zerocoins

import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.open_orders_book.Ask
import com.axiasoft.android.zerocoins.ui.features.available_books.viewmodels.TickerViewModel
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test


//Manual gen
class TestAsk {
    private lateinit var viewModel: TickerViewModel
    private lateinit var ask: Ask

    @Before
    fun setup(){
        //val mockResponse = viewModel.getListOrderBook()
        ask = Ask()
    }

    /*@Test
    fun `test a repo example`(){
        val mockResponse = viewModel.getListOrderBook()
    }*/


    @Test
    fun `test multiplicate for Asks`(){
        ask = Ask()
        val multiplicate = ask.multplicateAmount(3.0, 2.0)
        assertThat(multiplicate).isEqualTo(6.0)
    }

    @Test
    fun `test for sum`(){
        val ask = Ask()
        val sumA = ask.sumAmount(5,5)
        assertThat(sumA).isEqualTo(10)
    }

    @After
    fun `not sure`(){

    }
}