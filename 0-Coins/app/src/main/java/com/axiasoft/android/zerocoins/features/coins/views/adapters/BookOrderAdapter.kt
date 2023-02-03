package com.axiasoft.android.zerocoins.features.coins.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.axiasoft.android.zerocoins.R
import com.axiasoft.android.zerocoins.common.log
import com.axiasoft.android.zerocoins.features.coins.domain.mappers.CoinNameAndImage
import com.axiasoft.android.zerocoins.features.coins.domain.mappers.getBookOrderType
import com.axiasoft.android.zerocoins.features.coins.domain.models.data.book.response.Book
import com.bumptech.glide.Glide

class BookOrderAdapter: RecyclerView.Adapter<BookOrderAdapter.BookOrderViewHolder>() {

    var items: MutableList<Book> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookOrderViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.book_coin_item,
            parent,
            false)
        return BookOrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookOrderViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun updateBookOrders(newData: List<Book>) {
        items.clear()
        items.addAll(newData)
        notifyDataSetChanged()
    }

    class BookOrderViewHolder(private val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(book: Book) {
            val typeOfBookOrder = getBookOrderType(book.book ?: "") ?: CoinNameAndImage.any_any
            log("z0", "book is ${typeOfBookOrder.coinName}")
            Glide.with(binding.root.context)
                .load(typeOfBookOrder.coinImage)
                .centerInside()
                .into(binding.root.findViewById(R.id.iv_coin))
            binding.root.apply {
                findViewById<TextView>(R.id.tv_coin_name).text = typeOfBookOrder.coinName
                findViewById<TextView>(R.id.tv_item_num_info).text = book.maximumPrice
            }
            //binding.setVariable(R.id.tv_coin_name, book.book)
        }
    }
}