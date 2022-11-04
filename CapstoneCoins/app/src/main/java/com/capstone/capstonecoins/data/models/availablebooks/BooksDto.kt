package com.capstone.capstonecoins.data.models.availablebooks

import java.io.Serializable

data class BooksDto(
    val payload: List<Payload>,
    val success: Boolean
) : Serializable