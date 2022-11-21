package com.wizeline.criptocurrency.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wizeline.criptocurrency.domain.model.OpenOrder

@Entity(tableName = "bids_table")
data class BidsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "book") var book: String? = null,
    @ColumnInfo(name = "price") val price: String? = null,
    @ColumnInfo(name = "amount") val amount: String? = null
)

fun BidsEntity?.toBidsOpenOrderFromEntity() = OpenOrder(book = this?.book, price = this?.price, amount = this?.amount)

fun List<OpenOrder>?.toBidsEntityList() = mutableListOf<BidsEntity>().apply {
    this@toBidsEntityList?.map {
        this.add(
            BidsEntity(book = it.book, price = it.price, amount = it.amount)
        )
    }
}
