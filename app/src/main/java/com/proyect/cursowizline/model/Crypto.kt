package com.proyect.cursowizline.model

import android.os.Parcelable

@Parcelize
class Crypto(
    val book: String,
    val minimum_price:  String,
    val maximum_price:  String,
    val minimum_value:  String,
    val maximum_value:  String,
    val tick_size:      String,
    val minimum_amount: String,
    val maximum_amount: String,
) : Parcelable