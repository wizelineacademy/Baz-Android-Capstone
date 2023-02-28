package com.example.wizelineandroid.ui.adapter.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wizelineandroid.R
import com.example.wizelineandroid.data.local.entitys.BookEntity
import com.example.wizelineandroid.databinding.ItemRowBinding

class HomeAdapter(private val bookSelected: (BookEntity) -> Unit): ListAdapter<BookEntity, HomeAdapter.ViewHolder>(DiffUtilCallback)  {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemRowBinding.bind(view)

        @SuppressLint("SetTextI18n")
        fun bind(
            itemBook: BookEntity,
            bookSelected: (BookEntity) -> Unit,
        ) = with(binding) {
            val imgCoin = itemBook.itemName.split("_")
            txtn.text = "${imgCoin[0].uppercase()} - ${imgCoin[1].uppercase()}"
            Glide.with(itemView).load("https://cryptoicons.org/api/icon/${imgCoin[0]}/200").error(
                R.drawable.ic_baseline_image_24) .centerCrop().into(binding.circleImageView)
            binding.txtPminimo.text = itemBook.minimum_price
            binding.txtPmaximo.text = itemBook.maximum_price
            binding.cripto.setOnClickListener {
                bookSelected(itemBook)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), bookSelected)
    }

}

object DiffUtilCallback : DiffUtil.ItemCallback<BookEntity>() {
    override fun areItemsTheSame(oldItem: BookEntity, newItem: BookEntity): Boolean {
        return oldItem.itemName == newItem.itemName
    }

    override fun areContentsTheSame(oldItem: BookEntity, newItem: BookEntity): Boolean {
        return oldItem == newItem
    }
}