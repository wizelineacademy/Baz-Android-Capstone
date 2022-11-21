package com.wizeline.criptocurrency.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wizeline.criptocurrency.data.remote.dto.AvailableBookDto
import com.wizeline.criptocurrency.data.remote.dto.response.AvailableBooksResponse
import com.wizeline.criptocurrency.domain.model.AvailableBook

@Entity(tableName = "available_book_table")
data class AvailableBookEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "book") var book: String? = null,
    @ColumnInfo(name = "minimum_value") var minimum_value: String? = null,
    @ColumnInfo(name = "maximum_value") var maximum_value: String? = null
)

fun List<AvailableBook>?.toAvailableBookEntityList() = mutableListOf<AvailableBookEntity>()
    .apply {
        this@toAvailableBookEntityList?.forEach {
            this.add(
                AvailableBookEntity(
                    book = it.book,
                    minimum_value = it.minimum_value,
                    maximum_value = it.maximum_value
                )
            )
        }
    }

fun List<AvailableBookEntity>?.toAvailableBookListFromEntity() = mutableListOf<AvailableBook>()
    .apply {
        this@toAvailableBookListFromEntity?.forEach {
            this.add(
                AvailableBook(
                    book = it.book,
                    minimum_value = it.minimum_value,
                    maximum_value = it.maximum_value
                )
            )
        }
    }

fun List<AvailableBookEntity>?.toAvailableBookResponse() =
    AvailableBooksResponse(
        payload = this.toAvailableBookResponseListFromEntity()
    )

fun List<AvailableBookEntity>?.toAvailableBookResponseListFromEntity() = mutableListOf<AvailableBookDto>()
    .apply {
        // val availableBookDtoList :MutableList<AvailableBookDto> = mutableListOf()
        this@toAvailableBookResponseListFromEntity?.forEach {
            this.add(AvailableBookDto(it.book, it.minimum_value, it.maximum_value))
        }
    }
