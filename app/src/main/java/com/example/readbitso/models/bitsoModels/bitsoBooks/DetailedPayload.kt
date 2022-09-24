package com.example.readbitso.models.bitsoModels.bitsoBooks

data class DetailedPayload(
    var payload: BooksPayload,
    var shortname:String,
    var name:String="",
    var icon:Int
)