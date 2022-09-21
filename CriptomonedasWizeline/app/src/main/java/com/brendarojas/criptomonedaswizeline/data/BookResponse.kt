package com.brendarojas.criptomonedaswizeline.data

import com.google.gson.annotations.SerializedName

data class BookResponse (
    var success: Boolean,
    var payload: ArrayList<Book>
)