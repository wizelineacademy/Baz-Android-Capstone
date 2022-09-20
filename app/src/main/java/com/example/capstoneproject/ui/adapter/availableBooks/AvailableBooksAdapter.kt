package com.example.capstoneproject.ui.adapter.availableBooks


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.capstoneproject.R
import com.example.capstoneproject.data.model.AvailableBookModel

class AvailableBooksAdapter : ListAdapter<AvailableBookModel, BookViewHolder>(DiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.render(getItem(position))
    }

    private object DiffUtilCallback : DiffUtil.ItemCallback<AvailableBookModel>() {
        override fun areItemsTheSame(oldItem: AvailableBookModel, newItem: AvailableBookModel): Boolean = oldItem.book == newItem.book
        override fun areContentsTheSame(oldItem: AvailableBookModel, newItem: AvailableBookModel): Boolean = oldItem == newItem

    }
}