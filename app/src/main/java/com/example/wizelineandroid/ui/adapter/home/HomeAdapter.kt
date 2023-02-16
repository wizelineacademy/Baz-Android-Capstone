package com.example.wizelineandroid.ui.adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wizelineandroid.R
import com.example.wizelineandroid.core.BaseViewHolder
import com.example.wizelineandroid.data.local.BookEntity
import com.example.wizelineandroid.data.remote.model.ModelBook
import com.example.wizelineandroid.databinding.ItemRowBinding

class HomeAdapter(private val booksList: List<BookEntity>, private val itemClickListener: onUserClickListener)
    : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface onUserClickListener{
        fun onBookClick(book: BookEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itembinding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = MainViewHolder(itembinding, parent.context)

        // el click para cada pelicula
        itembinding.root.setOnClickListener {
            val position = holder.adapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?:return@setOnClickListener
            itemClickListener.onBookClick(booksList[position])
        }
        return holder

    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MainViewHolder -> holder.bind(booksList[position])
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<BookEntity>() {
            override fun areItemsTheSame(oldItem: BookEntity, newItem: BookEntity): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: BookEntity, newItem: BookEntity): Boolean {
                return oldItem.itemName == newItem.itemName
            }
        }
    }

    override fun getItemCount(): Int = booksList.size

    inner class MainViewHolder(val binding : ItemRowBinding, val context: Context): BaseViewHolder<BookEntity>(binding.root){
        override fun bind(item: BookEntity){
            binding.txtn.text = item.itemName
            val imgCoin = item.itemName.split("_")
            binding.txtPminimo.text = item.minimum_price
            binding.txtPmaximo.text = item.maximum_price
            context.let { Glide.with(it).load("https://cryptoicons.org/api/icon/${imgCoin[0]}/200" ?: R.drawable.ic_baseline_attach_money_24).centerCrop().into(binding.circleImageView) }




        }
    }
}