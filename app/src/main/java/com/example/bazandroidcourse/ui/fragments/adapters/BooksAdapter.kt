package com.example.bazandroidcourse.ui.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.databinding.BookItemLayoutBinding
import com.example.bazandroidcourse.ui.utils.cryptoName
import com.example.bazandroidcourse.ui.utils.getIcon
import com.squareup.picasso.Picasso

class BooksAdapter(
    val callback: (BookModel) -> (Unit)
) : ListAdapter<BookModel, BooksAdapter.ItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            BookItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BooksAdapter.ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewHolder(
        private val binding: BookItemLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BookModel) = with(binding) {
            tvName.text = item.book.cryptoName()
            try {
                Picasso.get().load(
                    item.getIcon()
                ).into(ivIcon)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            binding.root.setOnClickListener {
                callback(item)
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<BookModel>() {
    override fun areItemsTheSame(oldItem: BookModel, newItem: BookModel): Boolean =
        oldItem.book == newItem.book

    override fun areContentsTheSame(oldItem: BookModel, newItem: BookModel): Boolean =
        oldItem == newItem
}