package com.course.criptomonedas.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.course.criptomonedas.R
import com.course.criptomonedas.data.models.ModelBook
import com.course.criptomonedas.databinding.ItemBookBinding

class AdapterBooks(
    private var bookSelected: (ModelBook) -> Unit
) : ListAdapter<ModelBook, AdapterBooks.ViewHolder>(DiffUtilCallback) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemBookBinding.bind(view)

        fun bind(
            itemBook: ModelBook,
            bookSelected: (ModelBook) -> Unit
        ) = with(binding) {
            tvBook.text = itemBook.book
            val id = itemView.context.resources.getIdentifier(
                itemBook.book,
                "drawable",
                binding.root.context.packageName
            )
            iconCoin.setImageResource(id)
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

object DiffUtilCallback : DiffUtil.ItemCallback<ModelBook>() {
    override fun areItemsTheSame(oldItem: ModelBook, newItem: ModelBook): Boolean {
        return oldItem.book == newItem.book
    }

    override fun areContentsTheSame(oldItem: ModelBook, newItem: ModelBook): Boolean {
        return oldItem == newItem
    }
}