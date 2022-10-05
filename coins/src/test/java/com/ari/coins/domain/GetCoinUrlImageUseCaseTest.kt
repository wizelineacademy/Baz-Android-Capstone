package com.ari.coins.domain

import org.junit.Before
import org.junit.Test

internal class GetCoinUrlImageUseCaseTest {

    private lateinit var getCoinUrlImageUseCase: GetCoinUrlImageUseCase

    @Before
    fun setUp() {
        getCoinUrlImageUseCase = GetCoinUrlImageUseCase()
    }

    @Test
    fun `when we get url for coin then return the correct url`() {
        // Given
        val book = "eth_mxn"

        // When
        val result = getCoinUrlImageUseCase(book)

        // Then
        assert(result == "https://firebasestorage.googleapis.com/v0/b/crypto-d6420.appspot.com/o/cryptocurrency_icon%2Fic_crypto_eth.png?alt=media")
    }
}
