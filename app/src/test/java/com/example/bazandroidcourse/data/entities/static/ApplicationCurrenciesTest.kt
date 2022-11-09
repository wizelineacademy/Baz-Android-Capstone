package com.example.bazandroidcourse.data.entities.static

import org.junit.Test


/***
 * Unit tests of ApplicationCurrencies class using only junit
 */
class ApplicationCurrenciesTest {
    @Test
    fun `find by ticker returns valid currency`() {
        val result = ApplicationCurrencies.findByTicker("btc")
        assert(result == ApplicationCurrencies.Bitcoin)
    }

    @Test
    fun `find by ticker returns null`() {
        val result = ApplicationCurrencies.findByTicker("aaa_zzzz")
        assert(result == null)
    }

}