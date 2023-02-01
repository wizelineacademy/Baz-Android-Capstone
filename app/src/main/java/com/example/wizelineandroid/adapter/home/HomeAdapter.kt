package com.example.wizelineandroid.adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
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
            binding.txtPminimo.text = item.minimum_price
            binding.txtPmaximo.text = item.maximum_price
            when(item.book){
                "btc_mxn" -> binding.circleImageView.setImageResource(R.drawable.btc_mxn)
                "bat_mxn" -> binding.circleImageView.setImageResource(R.drawable.bat_mxn)
                "bch_mxn" -> binding.circleImageView.setImageResource(R.drawable.bch_mxn)
                "dai_mxn" -> binding.circleImageView.setImageResource(R.drawable.dai_mxn)
                "eth_mxn" -> binding.circleImageView.setImageResource(R.drawable.eth_mxn)
                "ltc_mxn" -> binding.circleImageView.setImageResource(R.drawable.ltc_mxn)
                "mana_mxn" -> binding.circleImageView.setImageResource(R.drawable.mana_mxn)
                "tusd_mxn" -> binding.circleImageView.setImageResource(R.drawable.tusd_mxn)
                "usd_mxn" -> binding.circleImageView.setImageResource(R.drawable.usd_mxn)
                "xrp_mxn" -> binding.circleImageView.setImageResource(R.drawable.xrp_mxn)
                else -> binding.circleImageView.setImageResource(com.google.android.material.R.drawable.navigation_empty_icon)
            }

        }
    }
}