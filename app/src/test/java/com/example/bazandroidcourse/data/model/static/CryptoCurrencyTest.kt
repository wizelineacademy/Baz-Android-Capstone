package com.example.bazandroidcourse.data.model.static

import com.example.bazandroidcourse.data.model.staticdata.ApplicationCurrency
import com.example.bazandroidcourse.data.model.staticdata.CryptoCurrency
import org.junit.Before
import org.junit.Test

class CryptoCurrencyTest {
    private lateinit var cryptoCurrency: CryptoCurrency

    @Before
    fun setUp() {
        cryptoCurrency = ApplicationCurrency.Bitcoin
    }

    @Test
    fun `itsMe returns true`() {
        // TODO:
    }

    @Test
    fun `itsMe returns false`() {
        // TODO:
    }
}
