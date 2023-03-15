package com.example.wizelineandroid.ui.adapter.detail

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wizelineandroid.R
import com.example.wizelineandroid.data.local.entitys.AskEntity
import com.example.wizelineandroid.databinding.ItemOrderBooksBinding

class AskAdapter : ListAdapter<AskEntity, AskAdapter.ViewHolder>(DiffUtilCallback) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemOrderBooksBinding.bind(view)

        @SuppressLint("SetTextI18n")
        fun bind(
            itemBook: AskEntity
        ) = with(binding) {
            binding.book.text = itemBook.book
            binding.price.text = itemBook.price
            binding.amount.text = itemBook.amount
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_order_books,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object DiffUtilCallback : DiffUtil.ItemCallback<AskEntity>() {
    override fun areItemsTheSame(oldItem: AskEntity, newItem: AskEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AskEntity, newItem: AskEntity): Boolean {
        return oldItem == newItem
    }
}
