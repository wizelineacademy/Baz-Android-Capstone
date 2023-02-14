package com.javg.cryptocurrencies.data.mapper

import com.javg.cryptocurrencies.data.model.CRYBookResponse
import com.google.common.truth.Truth.assertThat
import com.javg.cryptocurrencies.data.db.entity.CRYBookEntity
import com.javg.cryptocurrencies.data.model.CRYBook
import org.junit.Test

class CRYMapperKtTest {

    @Test
    fun toEntity() {
        val target = CRYBookResponse(
            book = "btc_mxn",
            minimumPrice  = ".003",
            maximumPrice  = "1000.00",
            minimumAmount = "100.00",
            maximumAmount = "1000000.00",
            minimumValue  = "25.00",
            maximumValue  = "1000000.00",
            tickSize      = "10",
            defaultChart  = "tradingview")
        assertThat(target.toEntity()).isInstanceOf(CRYBookEntity::class.java)
    }

    @Test
    fun toDomain() {
        val target = CRYBookEntity(
            book = "btc_mxn",
            minimumPrice = ".003",
            maximumPrice = "1000.00")

        assertThat(target.toDomain()).isInstanceOf(CRYBook::class.java)
    }
}