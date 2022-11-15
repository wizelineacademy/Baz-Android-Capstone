package com.capstone.capstonecoins.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capstone.capstonecoins.data.repository.models.Book
import com.capstone.capstonecoins.databinding.ItemCoinBinding


class CoinsAdapter(var itemClick: (Book) -> Unit) :
    ListAdapter<Book, CoinsAdapter.ViewHolder>(comparator) {
    object comparator : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }

    }

    //Todo Imagenes de CriptoMonedas
    inner class ViewHolder(private val binding: ItemCoinBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) = with(binding) {
            var image = ivBook.resources.getIdentifier(
                book.id,
                "drawable",
                root.context.packageName
            )
            ivBook.setImageResource(image)
            tvBook.text = book.id
            root.setOnClickListener {
                itemClick(book)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater =
            LayoutInflater.from(viewGroup.context)

        val binding = ItemCoinBinding.inflate(layoutInflater, viewGroup, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }

}
