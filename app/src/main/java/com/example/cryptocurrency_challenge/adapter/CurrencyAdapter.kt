package com.example.cryptocurrency_challenge.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrency_challenge.R
import com.example.cryptocurrency_challenge.data.model.Payload

@SuppressLint("SetTextI18n")
class CurrencyAdapter(private val dataSet: List<Payload>?) :
    RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val txtNameOfCurrency    : TextView
        private var imageOfCurrency      : ImageView
        private var viewItem           : View

        init {
            txtNameOfCurrency   = view.findViewById(R.id.txt_name_of_currency)
            imageOfCurrency     = view.findViewById(R.id.ic_currency)
            viewItem          = view.findViewById(R.id.content_available_books)
        }

        fun linkItem(dataSet: List<Payload>?){
            viewItem.setOnClickListener {
                Log.i("item clicked", "Clicked: ${dataSet?.get(adapterPosition)?.book}")
                viewItem.findNavController()
                    .navigate(
                        R.id.action_availableBooksFragment_to_detailCurrencysFragment2,
                        bundleOf("nameclicked" to "${dataSet?.get(adapterPosition)?.book}" ))
            }

            when(dataSet?.get(adapterPosition)?.book){
                "btc_mxn"   -> txtNameOfCurrency.text = "BTC"
                "eth_mxn"   -> txtNameOfCurrency.text = "ETH"
                "xrp_mxn"   -> txtNameOfCurrency.text = "XRP"
                "ltc_mxn"   -> txtNameOfCurrency.text = "LTC"
                "bch_mxn"   -> txtNameOfCurrency.text = "BCH"
                "tusd_mxn"  -> txtNameOfCurrency.text = "TUSD"
                "mana_mxn"  -> txtNameOfCurrency.text = "MANA"
                "bat_mxn"   -> txtNameOfCurrency.text = "BAT"
                "dai_mxn"   -> txtNameOfCurrency.text = "DAI"
                "usd_mxn"   -> txtNameOfCurrency.text = "USD"
                else -> {
                    txtNameOfCurrency.text = dataSet?.get(adapterPosition)?.book ?: " no name of currency"
                }
            }

            when(dataSet?.get(adapterPosition)?.book){
                "btc_mxn"   -> imageOfCurrency.setImageResource(R.mipmap.ic_btc_mxn)
                "eth_mxn"   -> imageOfCurrency.setImageResource(R.mipmap.ic_eth_mxn_foreground)
                "xrp_mxn"   -> imageOfCurrency.setImageResource(R.mipmap.ic_xrp_mxn)
                "ltc_mxn"   -> imageOfCurrency.setImageResource(R.mipmap.ic_ltc_mxn)
                "bch_mxn"   -> imageOfCurrency.setImageResource(R.mipmap.ic_bch_mxn)
                "tusd_mxn"  -> imageOfCurrency.setImageResource(R.mipmap.ic_tusd_mxn)
                "mana_mxn"  -> imageOfCurrency.setImageResource(R.mipmap.ic_mana_mxn)
                "bat_mxn"   -> imageOfCurrency.setImageResource(R.mipmap.ic_bat_mxn)
                "dai_mxn"   -> imageOfCurrency.setImageResource(R.mipmap.ic_dai_mxn)
                "usd_mxn"   -> imageOfCurrency.setImageResource(R.mipmap.ic_usd_mxn)
                else -> {
                    imageOfCurrency.setImageResource(R.drawable.ic_launcher_foreground)
                }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.crypto_currency_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.linkItem(dataSet)
    }

    override fun getItemCount() = dataSet?.size ?: -1

    override fun getItemViewType(position: Int): Int {
        return position
    }
}
