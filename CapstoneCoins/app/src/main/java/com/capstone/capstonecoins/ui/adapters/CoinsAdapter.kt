package com.capstone.capstonecoins.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.capstonecoins.data.repository.models.Book
import com.capstone.capstonecoins.databinding.ItemCoinBinding
import com.capstone.capstonecoins.ui.listeners.ListenerAdapter


class CoinsAdapter(private var dataSet: List<Book>, var setListener: ListenerAdapter) :
    RecyclerView.Adapter<CoinsAdapter.ViewHolder>() {
    //Todo cambiar adapter a listAdapter
    //Todo Imagenes de CriptoMonedas
    inner class ViewHolder(private val binding: ItemCoinBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) = with(binding) {
            tvBook.text = book.id

            root.setOnClickListener {
                setListener.listener(book)
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
        viewHolder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

}
