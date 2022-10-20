package com.example.bazandroidcourse.data.entities

data class BookModel(
    val book: String,
    val minimumAmount: String,
    val maximumAmount: String,
    val minimumPrice: String,
    val maximumPrice: String,
    val minimumValue: String,
    val maximumValue: String,
 ){
    override fun toString(): String {
        return "BookModel(book='$book', minimumAmount='$minimumAmount', maximumAmount='$maximumAmount', minimumPrice='$minimumPrice', maximumPrice='$maximumPrice', minimumValue='$minimumValue', maximumValue='$maximumValue')"
    }
}