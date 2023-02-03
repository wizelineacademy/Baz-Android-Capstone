package com.jpgl.cryptocurrencies.ui.adapter


import com.jpgl.cryptocurrencies.domain.model.BooksModelDomain
import com.jpgl.cryptocurrencies.ui.view.CryptoDetailFragment

interface OnCryptoSelectedItem {

    fun onItemListener(booksModelDomain: BooksModelDomain)

}