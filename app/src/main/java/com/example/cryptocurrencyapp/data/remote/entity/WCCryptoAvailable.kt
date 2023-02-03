package com.example.cryptocurrencyapp.data.remote.entity

import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.domain.entity.WCCOrderBookDTO
import com.example.cryptocurrencyapp.domain.entity.WCCryptoBookDTO
import com.example.cryptocurrencyapp.utils.Utils
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class WCCryptoAvailable(
    @SerializedName("book")
    @Expose
    val coin: String = "",

    @SerializedName("minimum_amount")
    @Expose
    val minAmount: String = "",

    @SerializedName("maximum_amount")
    @Expose
    val maxAmounut: String = "",

    @SerializedName("minimum_price")
    @Expose
    val minPrice: String = "",

    @SerializedName("maximum_price")
    @Expose
    val maxPrice: String = "",

    @SerializedName("minimum_value")
    @Expose
    val minValue: String = "",

    @SerializedName("maximum_value")
    @Expose
    val maxValue: String = ""
) {
    fun toBook(): WCCryptoBookDTO =
        when (coin) {
            Utils.CoinType.BITCOIN.value -> WCCryptoBookDTO(
                book = coin,
                name = Utils.CoinType.BITCOIN.coin,
                minPrice = minPrice,
                maxPrice = maxPrice,
                logo = R.drawable.ic_bitcoin
            )
            Utils.CoinType.ETHEREUM.value -> WCCryptoBookDTO(
                book = coin,
                name = Utils.CoinType.ETHEREUM.coin,
                minPrice = minPrice,
                maxPrice = maxPrice,
                logo = R.drawable.ic_ethereum
            )
            Utils.CoinType.XRP.value -> WCCryptoBookDTO(
                book = coin,
                name = Utils.CoinType.XRP.coin,
                minPrice = minPrice,
                maxPrice = maxPrice,
                logo = R.drawable.ic_xrp
            )
            Utils.CoinType.LITECOIN.value -> WCCryptoBookDTO(
                book = coin,
                name = Utils.CoinType.LITECOIN.coin,
                minPrice = minPrice,
                maxPrice = maxPrice,
                logo = R.drawable.ic_litecoin
            )
            Utils.CoinType.BITCOIN_CASH.value -> WCCryptoBookDTO(
                book = coin,
                name = Utils.CoinType.BITCOIN_CASH.coin,
                minPrice = minPrice,
                maxPrice = maxPrice,
                logo = R.drawable.ic_bitcoin_cash
            )
            Utils.CoinType.TRUEUSD.value -> WCCryptoBookDTO(
                book = coin,
                name = Utils.CoinType.TRUEUSD.coin,
                minPrice = minPrice,
                maxPrice = maxPrice,
                logo = R.drawable.ic_trueusd
            )
            Utils.CoinType.DECETRALAND.value -> WCCryptoBookDTO(
                book = coin,
                name = Utils.CoinType.DECETRALAND.coin,
                minPrice = minPrice,
                maxPrice = maxPrice,
                logo = R.drawable.ic_decentraland_mana
            )
            Utils.CoinType.BASIC_ATENTION_TOKEN.value -> WCCryptoBookDTO(
                book = coin,
                name = Utils.CoinType.BASIC_ATENTION_TOKEN.coin,
                minPrice = minPrice,
                maxPrice = maxPrice,
                logo = R.drawable.ic_basic_attention_token_bat
            )
            Utils.CoinType.DAI.value -> WCCryptoBookDTO(
                book = coin,
                name = Utils.CoinType.DAI.name,
                minPrice = minPrice,
                maxPrice = maxPrice,
                logo = R.drawable.ic_dai
            )
            Utils.CoinType.USD_COIN.value -> WCCryptoBookDTO(
                book = coin,
                name = Utils.CoinType.USD_COIN.coin,
                minPrice = minPrice,
                maxPrice = maxPrice,
                logo = R.drawable.ic_usd
            )
            else -> WCCryptoBookDTO()
        }

}

fun WCCryptoAvailable.toWCCryptoOrderBook() =
    WCCryptoBookDTO(
        book = coin,
        minPrice = minPrice,
        maxPrice = maxPrice,
    )

