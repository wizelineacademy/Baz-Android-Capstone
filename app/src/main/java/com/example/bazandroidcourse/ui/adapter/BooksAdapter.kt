package com.example.bazandroidcourse.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bazandroidcourse.R
import com.example.bazandroidcourse.data.entities.BookModel
import com.example.bazandroidcourse.data.utils.mappers.getCryptoName
import com.example.bazandroidcourse.databinding.BookItemLayoutBinding
import com.example.bazandroidcourse.ui.utils.getIcon
import com.squareup.picasso.Picasso

class BooksAdapter ( val callback:(BookModel)->(Unit)): ListAdapter<BookModel, BooksAdapter.ItemViewholder>(DiffCallback())  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            BookItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BooksAdapter.ItemViewholder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewholder(private val binding: BookItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BookModel) = with(binding) {
            tvName.text = item.book.getCryptoName()
            try {
                Picasso.get().load(
                    item.getIcon()
                ).into(ivIcon)
            }catch (e:Exception){
                e.printStackTrace()
            }
            binding.root.setOnClickListener {
               callback(item)
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<BookModel>() {
    override fun areItemsTheSame(oldItem: BookModel, newItem: BookModel): Boolean  = oldItem.book == newItem.book
    override fun areContentsTheSame(oldItem: BookModel, newItem: BookModel): Boolean = oldItem == newItem
}