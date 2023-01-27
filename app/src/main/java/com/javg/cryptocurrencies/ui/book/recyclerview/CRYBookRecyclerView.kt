package com.javg.cryptocurrencies.ui.book.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.javg.cryptocurrencies.data.model.CRYBook
import com.javg.cryptocurrencies.databinding.CryBookItemBinding

class CRYBookRecyclerView(
    private val listBook: MutableList<CRYBook>,
    private val context: Context,
    private val onItemClick: (String, Int) -> Unit): RecyclerView.Adapter<CRYBookRecyclerView.CRYBookViewHolder>() {

    inner class CRYBookViewHolder(private val binding: CryBookItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(book: CRYBook){
            binding.imageId.setImageDrawable(AppCompatResources.getDrawable(context, book.image))
            binding.txtBook.text = book.book
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CRYBookViewHolder {
        val binding: CryBookItemBinding = CryBookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CRYBookViewHolder(binding)
    }

    override fun getItemCount() = listBook.size

    override fun onBindViewHolder(holder: CRYBookViewHolder, position: Int) {
        holder.bind(listBook[position])
        holder.itemView.setOnClickListener { onItemClick(listBook[position].book,listBook[position].image) }
    }
}