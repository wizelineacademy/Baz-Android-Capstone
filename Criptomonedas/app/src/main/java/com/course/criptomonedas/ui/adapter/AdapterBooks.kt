package com.course.criptomonedas.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.course.criptomonedas.R
import com.course.criptomonedas.data.db.model.BooksEntity
import com.course.criptomonedas.databinding.ItemBookBinding

class AdapterBooks(
    private var bookSelected: (BooksEntity) -> Unit
) : ListAdapter<BooksEntity, AdapterBooks.ViewHolder>(DiffUtilCallback) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemBookBinding.bind(view)

        fun bind(
            itemBook: BooksEntity,
            bookSelected: (BooksEntity) -> Unit
        ) = with(binding) {
            tvBook.text = itemBook.name
            val id = itemView.context.resources.getIdentifier(
                itemBook.name,
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

object DiffUtilCallback : DiffUtil.ItemCallback<BooksEntity>() {
    override fun areItemsTheSame(oldItem: BooksEntity, newItem: BooksEntity): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: BooksEntity, newItem: BooksEntity): Boolean {
        return oldItem == newItem
    }
}