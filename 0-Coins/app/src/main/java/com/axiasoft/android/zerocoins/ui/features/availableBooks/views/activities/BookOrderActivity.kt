package com.axiasoft.android.zerocoins.ui.features.availableBooks.views.activities

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.axiasoft.android.zerocoins.R
import com.axiasoft.android.zerocoins.common.log
import com.axiasoft.android.zerocoins.databinding.ActivityBookOrderBinding
import com.axiasoft.android.zerocoins.ui.features.availableBooks.viewmodels.BookOrderViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookOrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookOrderBinding
    private val bookOrderViewModel: BookOrderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookOrderBinding.inflate(layoutInflater)

        supportActionBar?.title = getString(R.string.available_exchange_book_order_toolbar_title)

        setContentView(binding.root)

        bookOrderViewModel.internetStatus.observe(this) { isConnected ->
            log("z0", "internetStatus observer isConnected $isConnected")
            if (!isConnected) {
                showNoInternetSnackBar()
            }
        }
        if (!bookOrderViewModel.internetStatus.isNetworkAvailable()) {
            showNoInternetSnackBar()
        }
    }

    private fun showNoInternetSnackBar() {
        val snackBar = Snackbar.make(
            binding.root,
            getString(R.string.internet_status_no_internet_data_warning),
            Snackbar.LENGTH_INDEFINITE,
        )
        snackBar.setAction(android.R.string.ok) {
            snackBar.dismiss()
        }
        snackBar.setActionTextColor(R.color.white)
        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(Color.RED)
        val textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(Color.BLACK)
        textView.maxLines = 3
        snackBar.show()
    }
}
