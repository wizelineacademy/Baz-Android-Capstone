package com.example.bazandroidcourse.data.entities.static

sealed class ApplicationCurrencies(
    id: String,
    name: String,
    trading:Boolean = false
):CryptoCurrency(id, name, trading ) {
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
                PesoMX.itsMe(ticker)      -> PesoMX
                else -> null
            }
        }
        fun findByName(name:String):ApplicationCurrencies?{
            return when(name) {
                Bitcoin.javaClass.simpleName   -> Bitcoin
                Ethereum.javaClass.simpleName    -> Ethereum
                Ripple.javaClass.simpleName      -> Ripple
                Litecoin.javaClass.simpleName    -> Litecoin
                BitcoinCash.javaClass.simpleName -> BitcoinCash
                TrueUSD.javaClass.simpleName     -> TrueUSD
                Mana.javaClass.simpleName        -> Mana
                Bat.javaClass.simpleName         -> Bat
                Aave.javaClass.simpleName        -> Aave
                PesoMX.javaClass.simpleName      -> PesoMX
                else -> null
            }
        }
    }
    object  PesoMX: ApplicationCurrencies(
        "mxn",
        "Pesos MX",
        true
    )

    object Bitcoin: ApplicationCurrencies(
        "btc",
        "Bitcoin",
        true
    )

    object Ethereum: ApplicationCurrencies(
        "eth",
            "Ethereum"
    )

    object Ripple: ApplicationCurrencies(
        "xrp",
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




}







