package com.carteagal.baz_android.domain.mapper

import com.carteagal.baz_android.data.model.availableBook.AvailableBookResponse
import com.carteagal.baz_android.data.model.availableBook.AvailableBookUI
import com.carteagal.baz_android.utils.BaseUrlImage

fun availableMapper(list: List<AvailableBookResponse>): List<AvailableBookUI>{
    return list.map {
        AvailableBookUI(
            name = it.book,
            imageUrl = BaseUrlImage.generateUrlImage(it.book),
            maxPrice = it.maximumPrice.toDoubleOrNull() ?: 0.0,
            minPrice = it.minimumPrice.toDoubleOrNull() ?: 0.0,
            amount = it.maximumAmount.toDoubleOrNull() ?: 0.0
        )
    }.sortedBy { it.name }
}