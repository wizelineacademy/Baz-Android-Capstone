package com.proyect.cursowizline.domain.model

import android.os.Parcelable
import com.proyect.cursowizline.database.entities.CryptoEntity
import com.proyect.cursowizline.model.Crypto
import kotlinx.parcelize.Parcelize

@Parcelize
data class CryptoM(
    val book: String,
    val minimum_price: String,
    val maximum_price: String,
    val minimum_value: String,
    val maximum_value: String,
    val tick_size: String
) : Parcelable

fun Crypto.toDomain() =
    CryptoM(book, minimum_price, maximum_price, minimum_value, maximum_value, tick_size)

fun CryptoEntity.toDomain() =
    CryptoM(book, minimum_price, maximum_price, minimum_value, maximum_value, tick_size)
