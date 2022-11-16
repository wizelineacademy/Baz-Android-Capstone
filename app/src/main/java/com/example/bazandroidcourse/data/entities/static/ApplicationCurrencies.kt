package com.example.bazandroidcourse.data.entities.static

sealed class ApplicationCurrencies(
    id: String,
    name: String,
    trading: Boolean = false
) : CryptoCurrency(id, name, trading) {
    companion object {
        val supportedCurrencies by lazy {
            listOf(
                Bitcoin,
                Ethereum,
                Ripple,
                Litecoin,
                BitcoinCash,
                TrueUSD,
                Mana,
                Bat,
                Aave,
                PesoMX,
                NumARS,
                USD,
                Yearn,
                Shiba,
                Matic,
                Cardano,
                Solana,
                Sushi
            )
        }

        inline fun findByProperty(
            ticker: String,
            property: String,
            list: List<ApplicationCurrencies>,
            finder: (String, String) -> Boolean
        ): ApplicationCurrencies? {
            list.forEach {
                if (finder(ticker, property)) {
                    return it
                }
            }
            return null
        }

        fun findByTicker(ticker: String): ApplicationCurrencies? {
            supportedCurrencies.forEach {
                if (findByProperty(
                        ticker,
                        it.id,
                        supportedCurrencies,
                        { t, n -> t.startsWith("${n}_") }
                    ) != null
                ) {
                    return it
                }
            }
            return null
        }

        fun findByName(name: String): ApplicationCurrencies? {
            supportedCurrencies.forEach {
                if (findByProperty(
                        name,
                        it.name,
                        supportedCurrencies,
                        { t, n -> t.equals(n) }
                    ) != null
                ) {
                    return it
                }
            }
            return null
        }
    }

    object PesoMX : ApplicationCurrencies(
        "mxn",
        "Pesos MX",
        true
    )

    object Bitcoin : ApplicationCurrencies(
        "btc",
        "Bitcoin",
        true
    )

    object Ethereum : ApplicationCurrencies(
        "eth",
        "Ethereum"
    )

    object Ripple : ApplicationCurrencies(
        "xrp",
        "Ripple"
    )

    object Litecoin : ApplicationCurrencies(
        "ltc",
        "Litecoin"
    )

    object BitcoinCash : ApplicationCurrencies(
        "bch",
        "Bitcoint Cash",
        false
    )

    object TrueUSD : ApplicationCurrencies(
        "tusd",
        "TrueUSD"
    )

    object Mana : ApplicationCurrencies(
        "mana",
        "Decentraland"
    )

    object Bat : ApplicationCurrencies(
        "bat",
        "Basic Attention Token"
    )

    object Aave : ApplicationCurrencies(
        "aave",
        "Aave"
    )

    object NumARS : ApplicationCurrencies(
        "ars",
        "Num ARS",
        false
    )

    object USD : ApplicationCurrencies(
        "usd",
        "USD",
        true
    )

    object Yearn:ApplicationCurrencies(
        "yfi",
        "Yearn Finance"
    )
    object Matic:ApplicationCurrencies(
        "matic",
        "Polygon"
    )
    object Cardano:ApplicationCurrencies(
        "ada",
        "Cardano"
    )
    object Solana:ApplicationCurrencies(
        "sol",
        "Solana"
    )
    object Shiba:ApplicationCurrencies(
        "shib",
        "Shiba Inu"
    )
    object Sushi:ApplicationCurrencies(
        "sushi",
        "SushiSwap"
    )

}
