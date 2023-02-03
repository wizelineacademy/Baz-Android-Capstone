package com.proyect.cursowizline.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Crypto(
    val book: String,
    val minimum_price:  String,
    val maximum_price:  String,
    val minimum_value:  String,
    val maximum_value:  String,
    val tick_size:      String
) : Parcelable
