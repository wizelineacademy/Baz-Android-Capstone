package com.example.bazandroidcourse.ui.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ViewUtilsTest {
    lateinit var currentCurrency: String

    @Before
    fun setUp() {
        currentCurrency = "btc_mxn"
    }

    @Test
    fun `getTicker returns correct value`() {
        val result = getTicker(currentCurrency)
        assertThat(result).isEqualTo("bty")
    }

    @Test
    fun `get currency returns correct value`() {
        val result = getCurrency(currentCurrency)
        assertThat(result).isEqualTo("m")
    }
}
