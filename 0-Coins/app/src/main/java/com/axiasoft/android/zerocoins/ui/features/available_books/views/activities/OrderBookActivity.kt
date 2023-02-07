package com.axiasoft.android.zerocoins.ui.features.available_books.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.axiasoft.android.zerocoins.R
import com.axiasoft.android.zerocoins.common.log
import com.axiasoft.android.zerocoins.databinding.ActivityCoinsBinding
import com.axiasoft.android.zerocoins.network.app.InternetConnectionAvailableLiveData
import com.axiasoft.android.zerocoins.ui.features.available_books.views.fragments.BookOrderListFragment

class OrderBookActivity : AppCompatActivity() {

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

        //TODO move POC
        val internetConnectionStatus = InternetConnectionAvailableLiveData(application)

        internetConnectionStatus.observe(this, { isConnected ->
            if (isConnected) {
                //TODO set a flag on viewmodels
                log("z0", "impl net is $isConnected")
            }else{
                log("z0","Impl not connected $isConnected")
            }
        })
    }
}