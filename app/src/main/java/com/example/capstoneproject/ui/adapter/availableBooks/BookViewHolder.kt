package com.example.capstoneproject.ui.adapter.availableBooks

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.data.model.AvailableBookModel
import com.example.capstoneproject.databinding.ItemBookBinding

class BookViewHolder(view:View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemBookBinding.bind(view)

    @SuppressLint("SetTextI18n")
    fun render(book:AvailableBookModel) {
        with(binding){
            tvBookName.text = book.book
            tvMaximumPrice.text = "$${book.maximum_price}"
        }
    }
}