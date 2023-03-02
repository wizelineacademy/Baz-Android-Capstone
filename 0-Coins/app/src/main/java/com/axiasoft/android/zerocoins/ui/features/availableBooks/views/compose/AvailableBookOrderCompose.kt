package com.axiasoft.android.zerocoins.ui.features.availableBooks.views.compose

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.axiasoft.android.zerocoins.databinding.BookCoinItemBinding
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.mappers.CoinNameAndImage
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.mappers.CryptoCoinUI
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.mappers.genExchangeBookOrder
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.mappers.getCryptoCoinUI
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.exchange_order_book.ExchangeOrderBook

@Composable
fun ExchangeOrderBookList(
    availableExchangeOrderBooks: List<ExchangeOrderBook>,
    onClickItem: (ExchangeOrderBook) -> Unit,
) {
    val context = LocalContext.current
    val listState = rememberLazyListState()

    LazyColumn {
        itemsIndexed(availableExchangeOrderBooks) { index, item ->
            Spacer(modifier = Modifier.height(12.dp))
            AndroidViewBinding(BookCoinItemBinding::inflate) {
                val bookKey = item.book ?: CoinNameAndImage.any_any.coinKey
                val cryptoCoinsKeys = bookKey.split("_")

                val buyerCryptoCoinKey =
                    if (cryptoCoinsKeys.isEmpty()) CryptoCoinUI.crypto.coinKey else cryptoCoinsKeys[0]
                val sellerCryptoCoinKey =
                    if (cryptoCoinsKeys.size == 2) cryptoCoinsKeys[1] else CryptoCoinUI.crypto.coinKey

                val buyerCryptoCoinUI = getCryptoCoinUI(buyerCryptoCoinKey) ?: CryptoCoinUI.crypto
                val sellerCryptoCoinUI = getCryptoCoinUI(sellerCryptoCoinKey) ?: CryptoCoinUI.crypto

                val exchangerOrderBookName = genExchangeBookOrder(
                    buyerCryptoCoinUI,
                    buyerCryptoCoinKey,
                    sellerCryptoCoinUI,
                    sellerCryptoCoinKey,
                )
                this.apply {
                    coinsCv.setOnClickListener {
                        onClickItem(item)
                    }
                    layoutExchangeCoins.ivCoinOrigin.setImageResource(buyerCryptoCoinUI.coinImage)
                    layoutExchangeCoins.ivCoinTarget.setImageResource(sellerCryptoCoinUI.coinImage)
                    tvOrderBookCode.text = item.book
                    tvOrderBookName.text = exchangerOrderBookName
                    tvItemNumInfo.text = item.maximumPrice
                }
            }
        }
    }
}
