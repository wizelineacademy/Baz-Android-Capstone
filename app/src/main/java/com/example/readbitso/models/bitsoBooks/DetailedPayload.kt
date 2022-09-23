package com.example.readbitso.models.bitsoBooks

data class DetailedPayload(
    var payload: BooksPayload,
    var shortname:String,
    var name:String="",
    var icon:Int
)