package com.example.capstoneproject.data.model.availableBooks

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AvailableBookModel(
    val book: String,
    val maximum_price: String,
) : Parcelable