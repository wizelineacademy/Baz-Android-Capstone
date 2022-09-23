package com.baz.cmv.criptomonedas.coins.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baz.cmv.criptomonedas.R
import com.baz.cmv.criptomonedas.coins.Coins

class CoinsAdapter (val coins:List<Coins>): RecyclerView.Adapter<CoinsAdapter.CoinsViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsViewHolder {
            val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_criptomoneda, parent, false)
            return CoinsViewHolder(view)
        }

        override fun onBindViewHolder(viewholder: CoinsViewHolder, position: Int) {
            viewholder.criptomoneda.text = coins[position].criptomoneda
            viewholder.precio.text = coins[position].precio.toString()
        }

        override fun getItemCount(): Int {
            return coins.size
        }

        class CoinsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val criptomoneda = view.findViewById<TextView>(R.id.text_criptomoneda)
            val precio = view.findViewById<TextView>(R.id.text_precio)

        }

}


