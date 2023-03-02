package com.axiasoft.android.zerocoins.ui.features.availableBooks.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.axiasoft.android.zerocoins.R
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.mappers.CoinNameAndImage
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.mappers.CryptoCoinUI
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.mappers.genExchangeBookOrder
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.mappers.getCryptoCoinUI
import com.axiasoft.android.zerocoins.ui.features.availableBooks.domain.models.data.exchange_order_book.ExchangeOrderBook

class BookOrderAdapter(private val onItemClick: (ExchangeOrderBook) -> Unit) :
    RecyclerView.Adapter<BookOrderAdapter.BookOrderViewHolder>() {

    var items: MutableList<ExchangeOrderBook> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookOrderViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.book_coin_item,
            parent,
            false,
        )
        return BookOrderViewHolder(binding) {
            onItemClick(items[it])
        }
    }

    override fun onBindViewHolder(holder: BookOrderViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun updateBookOrders(newData: List<ExchangeOrderBook>) {
        items.clear()
        items.addAll(newData)
        notifyDataSetChanged()
    }

    class BookOrderViewHolder(
        private val binding: ViewDataBinding,
        onItemClicked: (Int) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClicked(bindingAdapterPosition)
            }
        }

        fun bind(book: ExchangeOrderBook) {
            val bookKey = book.book ?: CoinNameAndImage.any_any.coinKey
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

            binding.root.apply {
                findViewById<ImageView>(R.id.iv_coin_origin).setImageResource(buyerCryptoCoinUI.coinImage)
                findViewById<ImageView>(R.id.iv_coin_target).setImageResource(sellerCryptoCoinUI.coinImage)
                findViewById<TextView>(R.id.tv_order_book_code).text = book.book
                findViewById<TextView>(R.id.tv_order_book_name).text = exchangerOrderBookName
                findViewById<TextView>(R.id.tv_item_num_info).text = book.maximumPrice
            }
        }
    }
}
