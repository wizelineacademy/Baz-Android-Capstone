package com.axiasoft.android.zerocoins.ui.features.available_books.views.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.axiasoft.android.zerocoins.R
import com.axiasoft.android.zerocoins.common.log
import com.axiasoft.android.zerocoins.databinding.ActivityBookOrderBinding
import com.axiasoft.android.zerocoins.network.app.InternetConnectionAvailableLiveData
import com.axiasoft.android.zerocoins.ui.features.available_books.viewmodels.BookOrderViewModel
import com.axiasoft.android.zerocoins.ui.features.available_books.views.fragments.BookOrderListFragment

class BookOrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookOrderBinding
    val bookOrderViewModel: BookOrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookOrderBinding.inflate(layoutInflater)

        setContentView(binding.root)
        /*val internetConnectionStatus = InternetConnectionAvailableLiveData(application)

        internetConnectionStatus.observe(this) { isConnected ->
            bookOrderViewModel.isInternetAvailable = isConnected
            if (isConnected) {
                log("z0", "impl net is $isConnected")
            } else {
                log("z0", "Impl not connected $isConnected")
            }
        }*/
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.cl_cointainer, BookOrderListFragment.newInstance(), BookOrderListFragment.TAG)
                .commit()
        }
    }
}