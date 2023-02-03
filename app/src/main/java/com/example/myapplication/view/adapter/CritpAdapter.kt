package com.example.myapplication.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemCriptoBinding
import com.example.myapplication.model.Payload
import com.example.myapplication.util.formatAsCurrency
import com.example.myapplication.view.interfaces.OnclickListenerItem

/**
 * Created by: Juan Antonio Amado
 * date: 17,septiembre,2022
 */
class CritpAdapter(private val cellClickListener: OnclickListenerItem) : ListAdapter<Payload, CritpAdapter.CriptoViewHolder>(StockDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CriptoViewHolder {
        val binding: ItemCriptoBinding = ItemCriptoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CriptoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CriptoViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }

    inner class CriptoViewHolder(private val binding: ItemCriptoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Payload) {
            binding.cardCriptoCurrency.setOnClickListener {
                cellClickListener.onCellClickListener(item)
            }

            when (item.book) {
                "btc_mxn" -> {
                    binding.imageView.setBackgroundResource(R.drawable.ic_bitcoin)
                    binding.criptoName.setText(R.string.btc_mxn)
                    binding.priceMXN.text = item.maximum_amount.toDouble().formatAsCurrency()
                }
                "eth_mxn" -> {
                    binding.imageView.setBackgroundResource(R.drawable.ic_eth)
                    binding.criptoName.setText(R.string.eth_mxn)
                    binding.priceMXN.text = item.maximum_amount.toDouble().formatAsCurrency()
                }

                "xrp_mxn" -> {
                    binding.imageView.setBackgroundResource(R.drawable.ic_xrp)
                    binding.criptoName.setText(R.string.xrp_mxn)
                    binding.priceMXN.text = item.maximum_amount.toDouble().formatAsCurrency()
                }
                "ltc_mxn" -> {
                    binding.imageView.setBackgroundResource(R.drawable.ic_ltc)
                    binding.criptoName.setText(R.string.ltc_mxn)
                    binding.priceMXN.text = item.maximum_amount.toDouble().formatAsCurrency()
                }
                "bch_mxn" -> {
                    binding.imageView.setBackgroundResource(R.drawable.ic_bch)
                    binding.criptoName.setText(R.string.bch_mxn)
                    binding.priceMXN.text = item.maximum_amount.toDouble().formatAsCurrency()
                }
                "tusd_mxn" -> {
                    binding.imageView.setBackgroundResource(R.drawable.ic_tusd)
                    binding.criptoName.setText(R.string.tusd_mxn)
                    binding.priceMXN.text = item.maximum_amount.toDouble().formatAsCurrency()
                }
                "mana_mxn" -> {
                    binding.imageView.setBackgroundResource(R.drawable.ic_mana)
                    binding.criptoName.setText(R.string.mana_mxn)
                    binding.priceMXN.text = item.maximum_amount.toDouble().formatAsCurrency()
                }
                "bat_mxn" -> {
                    binding.imageView.setBackgroundResource(R.drawable.ic_bat)
                    binding.criptoName.setText(R.string.bat_mxn)
                    binding.priceMXN.text = item.maximum_amount.toDouble().formatAsCurrency()
                }
                "dai_mxn" -> {
                    binding.imageView.setBackgroundResource(R.drawable.ic_dai)
                    binding.criptoName.setText(R.string.dai_mxn)
                    binding.priceMXN.text = item.maximum_amount.toDouble().formatAsCurrency()
                }
                "usd_mxn" -> {
                    binding.imageView.setBackgroundResource(R.drawable.ic_usd)
                    binding.criptoName.setText(R.string.usd_mxn)
                    binding.priceMXN.text = item.maximum_amount.toDouble().formatAsCurrency()
                }
            }
        }
    }

    object StockDiffCallback : DiffUtil.ItemCallback<Payload>() {
        override fun areContentsTheSame(oldItem: Payload, newItem: Payload): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Payload, newItem: Payload): Boolean {
            return oldItem.book == newItem.book
        }
    }
}