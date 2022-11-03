package com.course.criptomonedas.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.course.criptomonedas.R
import com.course.criptomonedas.data.models.Payload
import com.course.criptomonedas.databinding.ItemBookBinding

class AdapterBooks(
    private var bookSelected: (Payload) -> Unit
) : ListAdapter<Payload, AdapterBooks.ViewHolder>(DiffUtilCallback) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemBookBinding.bind(view)

        fun bind(
            itemBook: Payload,
            bookSelected: (Payload) -> Unit
        ) = with(binding) {
            tvBook.text = itemBook.book
            binding.itemBook.setOnClickListener {
                bookSelected(itemBook)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), bookSelected)
    }
}

object DiffUtilCallback : DiffUtil.ItemCallback<Payload>() {
    override fun areItemsTheSame(oldItem: Payload, newItem: Payload): Boolean {
        return oldItem.book == newItem.book
    }

    override fun areContentsTheSame(oldItem: Payload, newItem: Payload): Boolean {
        return oldItem == newItem
    }
}