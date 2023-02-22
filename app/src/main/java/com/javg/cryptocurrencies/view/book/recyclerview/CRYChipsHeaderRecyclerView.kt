package com.javg.cryptocurrencies.view.book.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.javg.cryptocurrencies.R
import com.javg.cryptocurrencies.data.model.CRYBook
import com.javg.cryptocurrencies.databinding.CryChipsItemBinding
import com.javg.cryptocurrencies.utils.separateStringCoins

class CRYChipsHeaderRecyclerView(private val context: Context):
    ListAdapter<CRYBook, CRYChipsHeaderRecyclerView.CRyChipsViewHolder>(ChipsDiffCallback()){

    inner class CRyChipsViewHolder(private val binding: CryChipsItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(book: CRYBook, position: Int){
            if (position != 0)
                binding.clContainer.setBackgroundColor(ContextCompat.getColor(context,R.color.transparent))

            binding.txtTitleBook.text = book.book.separateStringCoins().uppercase()
            Glide.with(context)
                .load(book.imageUrl)
                .placeholder(R.drawable.ic_default_book)
                .into(binding.ivBook)

            binding.clContainer.setOnClickListener {
                if (position == absoluteAdapterPosition)
                    binding.clContainer.background = ContextCompat.getDrawable(context, R.drawable.background_button_oval)
                else
                    binding.clContainer.setBackgroundColor(ContextCompat.getColor(context,R.color.transparent))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CRyChipsViewHolder {
        val binding: CryChipsItemBinding = CryChipsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CRyChipsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CRyChipsViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }
}

class ChipsDiffCallback: DiffUtil.ItemCallback<CRYBook>(){
    override fun areItemsTheSame(oldItem: CRYBook, newItem: CRYBook) = oldItem.book == newItem.book

    override fun areContentsTheSame(oldItem: CRYBook, newItem: CRYBook) = oldItem == newItem

}