package com.androidcourse.data.static

import com.androidcourse.data.model.staticdata.ApplicationCurrency
import com.androidcourse.data.model.staticdata.CryptoCurrency
import org.junit.Before
import org.junit.Test

class CryptoCurrencyTest {
    private lateinit var cryptoCurrency: com.androidcourse.data.model.staticdata.CryptoCurrency

    @Before
    fun setUp() {
        cryptoCurrency = com.androidcourse.data.model.staticdata.ApplicationCurrency.Bitcoin
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
