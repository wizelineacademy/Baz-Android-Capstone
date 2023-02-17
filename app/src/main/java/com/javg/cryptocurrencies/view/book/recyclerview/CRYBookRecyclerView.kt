package com.javg.cryptocurrencies.view.book.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.javg.cryptocurrencies.R
import com.javg.cryptocurrencies.data.model.CRYBook
import com.javg.cryptocurrencies.databinding.CryBookItemBinding
import com.javg.cryptocurrencies.ext.getSecondCoinsText
import com.javg.cryptocurrencies.ext.separateStringCoins

/**
 * @author Juan Vera Gomez
 * Date modified 08/02/2023
 *
 * Contains the necessary functions for the adapter to
 * display cryptocurrency books to work
 *
 * @since 2.0
 */

class CRYBookRecyclerView(
    private val context: Context,
    private val onItemClick: (String, String) -> Unit):
    ListAdapter<CRYBook, CRYBookRecyclerView.CRYBookViewHolder>(BookOrderDiffCallback()) {

    inner class CRYBookViewHolder(private val binding: CryBookItemBinding): RecyclerView.ViewHolder(binding.root){

        /**
         * Function in charge of setting the information of the book model that it receives
         */
        fun bind(book: CRYBook){
            binding.txtBook.text = book.book.separateStringCoins().uppercase()
            binding.txtBookEnd.text = book.book.getSecondCoinsText().uppercase()
            Glide.with(context)
                .load(book.imageUrl)
                .placeholder(R.drawable.ic_default_book)
                .into(binding.imageId)
            Glide.with(context)
                .load(book.bookDestination)
                .placeholder(R.drawable.ic_default_book)
                .into(binding.imageIdEnd)
            binding.imageMore.setOnClickListener {
                onItemClick(book.book, book.imageUrl)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CRYBookViewHolder {
        val binding: CryBookItemBinding = CryBookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CRYBookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CRYBookViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class BookOrderDiffCallback: DiffUtil.ItemCallback<CRYBook>(){
    override fun areItemsTheSame(oldItem: CRYBook, newItem: CRYBook) = oldItem.book == newItem.book

    override fun areContentsTheSame(oldItem: CRYBook, newItem: CRYBook) = oldItem == newItem

}