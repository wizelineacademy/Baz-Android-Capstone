package com.example.bazandroidcourse.data.entities.static

sealed class ApplicationCurrencies(
    id: String,
    name: String
):CryptoCurrency(id, name) {
    companion object{
        fun findByTicker(ticker:String):ApplicationCurrencies?{
            return when {
                Bitcoin.itsMe(ticker)     -> Bitcoin
                Ethereum.itsMe(ticker)    -> Ethereum
                Ripple.itsMe(ticker)      -> Ripple
                Litecoin.itsMe(ticker)    -> Litecoin
                BitcoinCash.itsMe(ticker) -> BitcoinCash
                TrueUSD.itsMe(ticker)     -> TrueUSD
                Mana.itsMe(ticker)        -> Mana
                Bat.itsMe(ticker)         -> Bat
                Aave.itsMe(ticker)        -> Aave
                PesoMX.itsMe(ticker)      -> Aave
                else -> null
            }
        }
    }

    object Bitcoin: ApplicationCurrencies(
        "btc",
        "Bitcoin"
    )

    object Ethereum: ApplicationCurrencies(
        "eth",
            "Ethereum"
    )

    object Ripple: ApplicationCurrencies(
        "xpr",
        "Ripple"
    )

    object Litecoin:ApplicationCurrencies(
        "ltc",
        "Litecoin"
    )

    object BitcoinCash: ApplicationCurrencies(
        "bch",
        "Bitcoint Cash"
    )

    object  TrueUSD: ApplicationCurrencies(
        "tusd",
        "TrueUSD"
    )

    object  Mana: ApplicationCurrencies(
        "mana",
        "Decentraland"
    )
    object  Bat: ApplicationCurrencies(
        "bat",
        "Basic Attention Token"
    )

    object  Aave: ApplicationCurrencies(
        "aave",
        "Aave"
    )

    object  PesoMX: ApplicationCurrencies(
        "mxn",
        "Pesos MX"
    )


}







