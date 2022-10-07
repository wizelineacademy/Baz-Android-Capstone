package com.example.readbitso.models.bitsoModels.bitsoBooks

data class DetailedPayload(
    var payload: BooksPayload,
    var shortName: String,
    var name: String = "",
    var icon: Int
)
