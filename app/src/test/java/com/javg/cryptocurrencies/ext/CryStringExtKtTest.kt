package com.javg.cryptocurrencies.ext

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CryStringExtKtTest{

    @Test
    fun `test to separate string with symbol _ and get the first text`(){
        val text = "btc_mxn".separateStringCoins()
        assertThat(text).isEqualTo("btc")
    }

    @Test
    fun `test to separate string without symbol _ and get the first text`(){
        val text = "btc mxn".separateStringCoins()
        assertThat(text).isEqualTo("btc mxn")
    }

    @Test
    fun `test to separate empty string and get the first text`(){
        val text = "".separateStringCoins()
        assertThat(text).isEqualTo("")
    }

    @Test
    fun `test to separate string with a symbol other than _`(){
        val text = "btc%mxn".separateStringCoins()
        assertThat(text).isEqualTo("btc%mxn")
    }

    @Test
    fun `test to get the second text separated by the _ symbol`(){
        val text = "btc_mxn".getSecondCoinsText()
        assertThat(text).isEqualTo("mxn")
    }

    @Test
    fun `test to get the second separated text without the _ symbol`(){
        val text = "btc mxn".getSecondCoinsText()
        assertThat(text).isEqualTo("btc mxn")
    }

    @Test
    fun `test to get the second separated text`(){
        val text = "btc".getSecondCoinsText()
        assertThat(text).isEqualTo("btc")
    }
}