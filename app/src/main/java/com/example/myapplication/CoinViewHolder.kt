package com.example.myapplication

import android.view.View
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

class CoinViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(coin: Coin) {
        val name = view.findViewById<TextView>(R.id.coin_name)
        val symbol = view.findViewById<TextView>(R.id.coin_symbol)
        val price = view.findViewById<TextView>(R.id.coin_price)
        name.text = coin.name
        symbol.text = coin.symbol
        price.text = coin.price.toString()
    }
}