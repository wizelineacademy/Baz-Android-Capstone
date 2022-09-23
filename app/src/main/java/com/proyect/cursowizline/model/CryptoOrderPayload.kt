package com.proyect.cursowizline.model

class CryptoOrderPayload (

    val asks:       List<CryptoOrderDTO>,
    val sequence:   String,
    val updated_at: String,
    val bids:       List<CryptoOrderDTO>
)