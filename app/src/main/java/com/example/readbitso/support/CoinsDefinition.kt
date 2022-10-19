package com.example.readbitso.support

import com.example.readbitso.R

enum class CoinsDefinition (val token: String,
                            val shortName:String,
                            val icon:Int)
{
    Bitcoin(token="Bitcoin",shortName="Btc",icon= R.drawable.cripto_bitcoin),
    Ethereum(token="Ethereum",shortName="Eth",icon=R.drawable.cripto_ethereum),
    Xrp(token = "Xrp/Ripple", shortName = "Xrp",icon=R.drawable.cripto_xrp),
    Ltc(token = "Little Coin", shortName = "Ltc",icon=R.drawable.cripto_ltc),
    Bhc(token = "Bitcoin Cash", shortName ="Bch",icon=R.drawable.cripto_bhc ),
    Tusd(token = "True USD", shortName = "Tusd",icon=R.drawable.cripto_tusd),
    Mana(token = "Mana/Decentraland",shortName="Mana", icon = R.drawable.cripto_mana),
    Dai(token = "Dai", shortName = "Dai",icon=R.drawable.cripto_dai),
    Usd(token ="Usd",shortName="Usd",icon=R.drawable.cripto_usd),
    Bat(token = "Basic Atenttion Token",shortName="Bat",icon=R.drawable.cripto_bat)
}

