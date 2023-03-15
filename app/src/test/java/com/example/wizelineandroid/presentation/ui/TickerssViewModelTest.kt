package com.example.wizelineandroid.presentation.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.wizelineandroid.data.remote.model.GetTickers
import com.example.wizelineandroid.presentation.ticker.TickerBooksViewModel
import com.example.wizelineandroid.repository.ticker.TickerRepo
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TickerssViewModelTest {

    @RelaxedMockK
    private lateinit var tickerRoom: TickerRepo

    @get:Rule
    var instantExcecutorRule = InstantTaskExecutorRule()


    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
    }

    @Test
    fun addProductTest() {
        val book = "btc_mxn"
        val getDetails = TickerBooksViewModel(tickerRoom)

        val observer = Observer<GetTickers> {}

        try {
            getDetails.detailBooks.observeForever(observer)
            val result = getDetails.fetchTickersBooks(book)

            MatcherAssert.assertThat(result, CoreMatchers.`is`(true))
        } finally {
            getDetails.detailBooks.removeObserver(observer)
        }

    }
}