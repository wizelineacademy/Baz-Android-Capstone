package com.example.bazandroidcourse.data.datasource.remote.api.response

data class BookDetailResponse(
    var success: Boolean? = null,
    var payload: DetailResponse
)

data class DetailResponse(
    var book    : String? = null,
    var volume  : String? = null,
    var high    : String? = null,
    var last    : String? = null,
    var low     : String? = null,
)