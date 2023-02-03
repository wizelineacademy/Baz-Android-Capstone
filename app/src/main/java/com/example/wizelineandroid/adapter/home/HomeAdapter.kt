package com.example.wizelineandroid.adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wizelineandroid.R
import com.example.wizelineandroid.core.BaseViewHolder
import com.example.wizelineandroid.data.model.ModelBook
import com.example.wizelineandroid.databinding.ItemRowBinding

class HomeAdapter(private val booksList: List<ModelBook>, private val itemClickListener: onUserClickListener): RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface onUserClickListener{
        fun onBookClick(book: ModelBook)
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

    override fun getItemCount(): Int = booksList.size

    inner class MainViewHolder(val binding : ItemRowBinding, val context: Context): BaseViewHolder<ModelBook>(binding.root){
        override fun bind(item: ModelBook){
            binding.txtn.text = item.book
            val imgCoin = item.book.split("_")
            binding.txtPminimo.text = item.minimum_price
            binding.txtPmaximo.text = item.maximum_price
            context.let { Glide.with(it).load("https://cryptoicons.org/api/icon/${imgCoin[0]}/200").centerCrop().into(binding.circleImageView) }
        }
    }
}