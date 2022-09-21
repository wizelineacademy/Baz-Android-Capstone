package com.example.capstoneproject.ui.adapter.availableBooks

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.capstoneproject.R
import com.example.capstoneproject.data.model.availableBooks.AvailableBookModel
import com.example.capstoneproject.databinding.ItemBookBinding

class BookViewHolder(view:View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemBookBinding.bind(view)

    @SuppressLint("SetTextI18n")
    fun render(book: AvailableBookModel, onClickItem:(AvailableBookModel)-> Unit) {

        val coin = book.book.split('_')


        binding.apply{
            tvBookName.text = book.book
            tvMaximumPrice.text = "$${book.maximum_price}"

            Glide.with(binding.root.context)
                .load("https://assets.coincap.io/assets/icons/${coin[0]}@2x.png")
                .placeholder(R.drawable.item_currency_placeholder)
                .error(R.drawable.item_book_error)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .transform(CenterCrop()).into(ivIcBook)
        }

        itemView.setOnClickListener {
            onClickItem(book)
        }
    }
}