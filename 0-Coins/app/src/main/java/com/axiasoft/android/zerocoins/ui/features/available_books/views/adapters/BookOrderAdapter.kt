package com.axiasoft.android.zerocoins.ui.features.available_books.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.axiasoft.android.zerocoins.R
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.mappers.CoinNameAndImage
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.mappers.getBookOrderType
import com.axiasoft.android.zerocoins.ui.features.available_books.domain.models.data.exchange_order_book.ExchangeOrderBook
import com.bumptech.glide.Glide

class BookOrderAdapter(private val onItemClick: (ExchangeOrderBook) -> Unit) : RecyclerView.Adapter<BookOrderAdapter.BookOrderViewHolder>() {

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
            val typeOfBookOrder = getBookOrderType(book.book ?: "") ?: CoinNameAndImage.any_any
            Glide.with(binding.root.context)
                .load(typeOfBookOrder.coinImage)
                .centerInside()
                .into(binding.root.findViewById(R.id.iv_coin))
            binding.root.apply {
                findViewById<TextView>(R.id.tv_order_book_code).text = book.book
                findViewById<TextView>(R.id.tv_order_book_name).text = typeOfBookOrder.coinName
                findViewById<TextView>(R.id.tv_item_num_info).text = book.maximumPrice
            }
        }
    }
}
