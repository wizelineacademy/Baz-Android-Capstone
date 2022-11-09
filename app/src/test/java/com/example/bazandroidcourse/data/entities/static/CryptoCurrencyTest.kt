package com.example.bazandroidcourse.data.entities.static

import com.google.common.truth.Truth.*
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
        val result = cryptoCurrency.itsMe("btc_mxn")
        assertThat( result).isTrue()
    }
}