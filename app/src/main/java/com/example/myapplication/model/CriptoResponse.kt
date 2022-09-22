package com.example.myapplication.model

data class CriptoResponse(
    val payload: List<Payload>,
    val success: Boolean
)