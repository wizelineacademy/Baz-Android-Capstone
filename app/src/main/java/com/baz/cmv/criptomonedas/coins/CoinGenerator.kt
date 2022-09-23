package com.baz.cmv.criptomonedas.coins

object CoinGenerator {

    private val moneda1 = Coins(
        criptomoneda = "criptomoneda1",
        precio = 120.0
    )

    private val moneda2 = Coins(
        criptomoneda = "criptomoneda2",
        precio = 130.0
    )

    val coins :List<Coins> = listOf (
    Coins("criptomoneda1",120.0),
    Coins("criptomoneda2",130.0),
        Coins("criptomoneda3",130.0)
   )

}