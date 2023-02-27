package com.example.wizelineandroid.ui.adapter.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wizelineandroid.R
import com.example.wizelineandroid.core.BaseViewHolder
import com.example.wizelineandroid.data.local.BookEntity
import com.example.wizelineandroid.databinding.ItemRowBinding

class HomeAdapter(private val booksList: List<BookEntity>, private val itemClickListener: OnUserClickListener)
    : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnUserClickListener{
        fun onBookClick(book: BookEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itembinding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = MainViewHolder(itembinding, parent.context)

        itembinding.root.setOnClickListener {
            val position = holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
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

    override fun getItemCount(): Int = booksList.size

    inner class MainViewHolder(val binding : ItemRowBinding, val context: Context): BaseViewHolder<BookEntity>(binding.root){
        @SuppressLint("SetTextI18n")
        override fun bind(item: BookEntity){
            val imgCoin = item.itemName.split("_")
            binding.txtn.text = "${imgCoin[0].uppercase()} - ${imgCoin[1].uppercase()}"
            binding.txtPminimo.text = item.minimum_price
            binding.txtPmaximo.text = item.maximum_price
            context.let { Glide.with(it).load("https://cryptoicons.org/api/icon/${imgCoin[0]}/200").error(
                R.drawable.ic_baseline_image_24) .centerCrop().into(binding.circleImageView) }

        }
    }
}