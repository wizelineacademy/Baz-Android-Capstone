package com.example.bazandroidcourse.data.entities.static

import org.junit.Before
import org.junit.Test

class CryptoCurrencyTest {
    private lateinit var cryptoCurrency: CryptoCurrency

    @Before
    fun setUp() {
        cryptoCurrency = ApplicationCurrencies.Bitcoin
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
