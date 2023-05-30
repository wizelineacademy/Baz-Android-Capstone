package com.androidcourse.data.static

import com.andcourse.domain.model.staticdata.ApplicationCurrency
import org.junit.Test

/***
 * Unit tests of ApplicationCurrencies class using only junit
 */
class ApplicationCurrenciesTest {
    @Test
    fun `find by ticker returns valid currency`() {
        val result = ApplicationCurrency.findByTicker("btc_mxn")
        assert(result == ApplicationCurrency.Bitcoin)
    }

    @Test
    fun `find by ticker returns null`() {
        val result = ApplicationCurrency.findByTicker("aaa_zzzz")
        assert(result == null)
    }
}
