package com.example.cryptocurrencyapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cryptocurrencyapp.domain.entity.WCCryptoBookDTO

@Entity(tableName = "available_table")
data class AvailableBookEntity(
    @PrimaryKey
    val book: String,
    val name: String = "",
    val minPrice: String = "",
    val maxPrice: String = "",
    val logo: Int,
)
fun AvailableBookEntity.toWCCryptoBookDTO() =
    WCCryptoBookDTO(
        book = book,
        name = name,
        minPrice = minPrice,
        maxPrice = maxPrice,
        logo = logo
    )

fun List<WCCryptoBookDTO>?.toAvailableEntity() = mutableListOf<AvailableBookEntity>().apply {
    this@toAvailableEntity?.forEach {
        this.add(
            AvailableBookEntity(
                book = it.book,
                name = it.name,
                minPrice = it.minPrice,
                maxPrice = it.maxPrice,
                logo = it.logo
            )
        )
    }
}