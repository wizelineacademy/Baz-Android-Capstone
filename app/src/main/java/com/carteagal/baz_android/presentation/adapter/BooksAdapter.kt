package com.carteagal.baz_android.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.carteagal.baz_android.data.model.availableBook.AvailableBookUI
import com.carteagal.baz_android.databinding.ItemBookListBinding
import com.carteagal.baz_android.utils.extension.loadImage
import com.carteagal.baz_android.utils.extension.toAmountFormat

class BooksAdapter(
    private val onClickListener: (bookInfo: AvailableBookUI) -> Unit
): ListAdapter<AvailableBookUI, BooksAdapter.ViewHolder>(AvailableBookCallBack){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemBookListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    inner class ViewHolder(private val binding: ItemBookListBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(book: AvailableBookUI){
            binding.txtNameBook.text = book.name
            binding.txtMaxAmount.text = book.maxPrice.toAmountFormat()
            binding.txtMinAmount.text = book.minPrice.toAmountFormat()
            binding.imgLogo.loadImage(book.imageUrl)
            binding.cardViewInfo.setOnClickListener { onClickListener(book) }
            //binding.txtMinAmount.text = String.format("%.2f", book.minimumPrice)
        }
    }

    private object AvailableBookCallBack: DiffUtil.ItemCallback<AvailableBookUI>(){
        override fun areItemsTheSame(
            oldItem: AvailableBookUI,
            newItem: AvailableBookUI
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: AvailableBookUI,
            newItem: AvailableBookUI
        ): Boolean {
            return oldItem == newItem
        }

    }
}