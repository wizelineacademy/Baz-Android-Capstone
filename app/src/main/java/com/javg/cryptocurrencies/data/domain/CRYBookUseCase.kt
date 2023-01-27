package com.javg.cryptocurrencies.data.domain

import com.javg.cryptocurrencies.R
import com.javg.cryptocurrencies.utils.CRYUtils
import com.javg.cryptocurrencies.data.model.CRYBook
import com.javg.cryptocurrencies.data.model.CRYBookResponse
import com.javg.cryptocurrencies.data.repository.CRYBookRepository
import com.javg.cryptocurrencies.utils.CRYConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CRYBookUseCase @Inject constructor(
    private val repository: CRYBookRepository){

    suspend operator fun invoke(): MutableList<CRYBook> = withContext(Dispatchers.IO){
        val books = repository.getAvailableBooks()
        val listBookAux = mutableListOf<CRYBook>()
        if (!books.payload.isNullOrEmpty()){
            books.payload?.forEach {
                val image = when(CRYUtils.separateStringCoin(it.book)){
                    CRYConstant.BTC -> R.drawable.ic_bitcoin
                    CRYConstant.ETH -> R.drawable.ic_ethereum
                    CRYConstant.XRP -> R.drawable.ic_xrp
                    CRYConstant.LTC -> R.drawable.ic_litecoin
                    CRYConstant.BCH -> R.drawable.ic_bitcoin_cash
                    CRYConstant.TUSD -> R.drawable.ic_tether
                    CRYConstant.MANA -> R.drawable.ic_monero
                    CRYConstant.BAT -> R.drawable.ic_avalanche
                    CRYConstant.DAI -> R.drawable.ic_dai
                    CRYConstant.COMP -> R.drawable.ic_compound
                    CRYConstant.LINK -> R.drawable.ic_chainlink
                    CRYConstant.UNI -> R.drawable.ic_uniswap
                    CRYConstant.AAVE -> R.drawable.ic_aave
                    CRYConstant.CHZ -> R.drawable.ic_zcash
                    CRYConstant.AXS -> R.drawable.ic_eos
                    CRYConstant.DYDX -> R.drawable.ic_filecoin
                    CRYConstant.SHIB -> R.drawable.ic_shiba
                    CRYConstant.MATIC -> R.drawable.ic_polygon
                    else -> R.drawable.ic_pancakeswap
                }
                listBookAux.add(CRYBook(it.book,image))
            }
        }
        listBookAux
    }
}