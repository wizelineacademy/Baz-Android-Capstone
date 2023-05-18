package com.example.bazandroidcourse.data.model.staticdata

sealed class ApplicationCurrency(
    id: String ="",
    name: String = "",
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
            list: List<ApplicationCurrency>,
            finder: (String, String) -> Boolean
        ): ApplicationCurrency? {
            list.forEach {
                if (finder(ticker, property)) {
                    return it
                }
            }
            return null
        }

        fun findByTicker(ticker: String): ApplicationCurrency? {
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

        fun findByName(name: String): ApplicationCurrency? {
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

    object PesoMX : ApplicationCurrency(
        "mxn",
        "Pesos MX",
        true
    )

    object Bitcoin : ApplicationCurrency(
        "btc",
        "Bitcoin",
        true
    )

    object Ethereum : ApplicationCurrency(
        "eth",
        "Ethereum"
    )

    object Ripple : ApplicationCurrency(
        "xrp",
        "Ripple"
    )

    object Litecoin : ApplicationCurrency(
        "ltc",
        "Litecoin"
    )

    object BitcoinCash : ApplicationCurrency(
        "bch",
        "Bitcoint Cash",
        false
    )

    object TrueUSD : ApplicationCurrency(
        "tusd",
        "TrueUSD"
    )

    object Mana : ApplicationCurrency(
        "mana",
        "Decentraland"
    )

    object Bat : ApplicationCurrency(
        "bat",
        "Basic Attention Token"
    )

    object Aave : ApplicationCurrency(
        "aave",
        "Aave"
    )

    object NumARS : ApplicationCurrency(
        "ars",
        "Num ARS",
        false
    )

    object USD : ApplicationCurrency(
        "usd",
        "USD",
        true
    )

    object Yearn: ApplicationCurrency(
        "yfi",
        "Yearn Finance"
    )
    object Matic: ApplicationCurrency(
        "matic",
        "Polygon"
    )
    object Cardano: ApplicationCurrency(
        "ada",
        "Cardano"
    )
    object Solana: ApplicationCurrency(
        "sol",
        "Solana"
    )
    object Shiba: ApplicationCurrency(
        "shib",
        "Shiba Inu"
    )
    object Sushi: ApplicationCurrency(
        "sushi",
        "SushiSwap"
    )

}
