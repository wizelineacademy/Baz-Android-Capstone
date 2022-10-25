package com.example.bazandroidcourse.data.entities.static

class BitCoin:CriptoInterface {
    override val id: String
        get() = "btc"

    override val name: String
        get() = "Bit Coin"

    override fun getUrlIcon(): String {
        TODO("Not yet ")
    }
}