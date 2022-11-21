package com.wizeline.criptocurrency

import com.wizeline.criptocurrency.common.adapters.utilities.formatAsCurrency
import org.junit.Assert.assertEquals
import org.junit.Test

class UtilsTest {

    @Test
    fun `formatted Double value into currency`() {
        // Given
        val bookCodeStr: Double = 300.00

        // When
        val result = bookCodeStr.formatAsCurrency()

        // Then
        assertEquals(result, "$300.00")
    }
}
