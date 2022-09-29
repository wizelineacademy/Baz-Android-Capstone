package com.example.myapplication.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemCriptoBinding
import com.example.myapplication.model.CriptoCurrency
import com.example.myapplication.model.Payload
import com.example.myapplication.util.formatAsCurrency
import com.example.myapplication.view.interfaces.OnclickListenerItem

/**
 * Created by: Juan Antonio Amado
 * date: 17,septiembre,2022
 */
class CritpAdapter(private val cellClickListener: OnclickListenerItem) : ListAdapter<CriptoCurrency, CritpAdapter.CriptoViewHolder>(StockDiffCallback) {

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
        fun bind(item: CriptoCurrency) {

            binding.priceMXN.text = item.price.toDouble().formatAsCurrency()
            binding.cardCriptoCurrency.setOnClickListener {
                cellClickListener.onCellClickListener(item)
            }

            when (item.name) {
                "btc_mxn" -> {
                    binding.imageView.setBackgroundResource(R.drawable.ic_bitcoin)
                    binding.criptoName.setText(R.string.btc_mxn)

                }
                "eth_mxn" -> {
                    binding.imageView.setBackgroundResource(R.drawable.ic_eth)
                    binding.criptoName.setText(R.string.eth_mxn)

                }

                "xrp_mxn" -> {
                    binding.imageView.setBackgroundResource(R.drawable.ic_xrp)
                    binding.criptoName.setText(R.string.xrp_mxn)

                }
                "ltc_mxn" -> {
                    binding.imageView.setBackgroundResource(R.drawable.ic_ltc)
                    binding.criptoName.setText(R.string.ltc_mxn)

                }
                "bch_mxn" -> {
                    binding.imageView.setBackgroundResource(R.drawable.ic_bch)
                    binding.criptoName.setText(R.string.bch_mxn)

                }
                "tusd_mxn" -> {
                    binding.imageView.setBackgroundResource(R.drawable.ic_tusd)
                    binding.criptoName.setText(R.string.tusd_mxn)

                }
                "mana_mxn" -> {
                    binding.imageView.setBackgroundResource(R.drawable.ic_mana)
                    binding.criptoName.setText(R.string.mana_mxn)

                }
                "bat_mxn" -> {
                    binding.imageView.setBackgroundResource(R.drawable.ic_bat)
                    binding.criptoName.setText(R.string.bat_mxn)

                }
                "dai_mxn" -> {
                    binding.imageView.setBackgroundResource(R.drawable.ic_dai)
                    binding.criptoName.setText(R.string.dai_mxn)

                }
                "usd_mxn" -> {
                    binding.imageView.setBackgroundResource(R.drawable.ic_usd)
                    binding.criptoName.setText(R.string.usd_mxn)
                }
                else ->{
                    binding.criptoName.text = item.name
                    binding.imageView.setBackgroundResource(R.drawable.ic_default_coin)
                }
            }
        }
    }

    object StockDiffCallback : DiffUtil.ItemCallback<CriptoCurrency>() {
        override fun areContentsTheSame(oldItem: CriptoCurrency, newItem: CriptoCurrency): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: CriptoCurrency, newItem: CriptoCurrency): Boolean {
            return oldItem == newItem
        }
    }
}