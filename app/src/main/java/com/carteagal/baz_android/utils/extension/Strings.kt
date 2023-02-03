package com.carteagal.baz_android.utils.extension

import android.icu.text.NumberFormat
import java.util.*

fun Double.toAmountFormat(): String =
    NumberFormat
        .getCurrencyInstance(Locale.US)
        .format(this)
