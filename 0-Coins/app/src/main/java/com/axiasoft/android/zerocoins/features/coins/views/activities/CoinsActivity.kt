package com.axiasoft.android.zerocoins.features.coins.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.axiasoft.android.zerocoins.R
import com.axiasoft.android.zerocoins.databinding.ActivityCoinsBinding
import com.axiasoft.android.zerocoins.features.coins.viewmodels.BooksScreenViewModel
import com.axiasoft.android.zerocoins.features.coins.views.fragments.BookOrderListFragment

class CoinsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoinsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinsBinding.inflate(layoutInflater)

        setContentView(binding.root)
        //viewModel.getBooks()
        /*viewModel.getBooksWithUseCase()
        viewModel.books.observeForever {
            binding.tvTest.text = it.toString()
        }*/

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.cl_cointainer, BookOrderListFragment.newInstance(), BookOrderListFragment.TAG)
                .commit()
        }
    }
}