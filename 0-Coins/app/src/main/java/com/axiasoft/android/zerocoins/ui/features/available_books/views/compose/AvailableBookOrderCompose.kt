package com.axiasoft.android.zerocoins.ui.features.available_books.views.compose

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
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.mappers.CoinNameAndImage
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.mappers.getBookOrderType
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.exchange_order_book.ExchangeOrderBook
import com.bumptech.glide.Glide

@Composable
fun ExchangeOrderBookList(
    availableExchangeOrderBooks: List<ExchangeOrderBook>,
    onClickItem: (ExchangeOrderBook) -> Unit
) {
    val context = LocalContext.current
    val listState = rememberLazyListState()

    LazyColumn {
        itemsIndexed(availableExchangeOrderBooks) { index, item ->
            Spacer(modifier = Modifier.height(12.dp))
            AndroidViewBinding(BookCoinItemBinding::inflate) {
                val typeOfBookOrder = getBookOrderType(item.book ?: "") ?: CoinNameAndImage.any_any
                Glide.with(context)
                    .load(typeOfBookOrder.coinImage)
                    .centerInside()
                    .into(this.ivCoin)
                this.apply {
                    coinsCv.setOnClickListener {
                        onClickItem(item)
                    }
                    tvOrderBookCode.text = item.book
                    tvOrderBookName.text = typeOfBookOrder.coinName
                    tvItemNumInfo.text = item.maximumPrice
                }
            }
        }
    }
}
