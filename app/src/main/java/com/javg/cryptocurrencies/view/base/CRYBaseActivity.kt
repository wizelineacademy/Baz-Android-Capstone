package com.javg.cryptocurrencies.view.base

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import com.javg.cryptocurrencies.R
import com.javg.cryptocurrencies.view.viewmodel.CRYAppViewModel
import kotlinx.coroutines.launch

open class CRYBaseActivity : AppCompatActivity() {
    private val appVM by viewModels<CRYAppViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        testStateFlow()
    }

    private fun testStateFlow() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                appVM.uiStateNetwork.collect {
                    if (it) {
                        showSuccessNetwork()
                    } else {
                        showErrorNetwork()
                    }
                }
            }
        }
    }

    private fun showSuccessNetwork() {
        val snack = Snackbar.make(
            this@CRYBaseActivity.findViewById(R.id.nav_host_main),
            resources.getString(R.string.cry_success_network),
            Snackbar.LENGTH_LONG,
        )
        snack.setBackgroundTint(ContextCompat.getColor(this, R.color.green_success))
        snack.show()
    }

    private fun showErrorNetwork() {
        val snack = Snackbar.make(
            this@CRYBaseActivity.findViewById(R.id.nav_host_main),
            resources.getString(R.string.cry_error_network),
            Snackbar.LENGTH_LONG,
        )
        snack.setBackgroundTint(ContextCompat.getColor(this, R.color.red_error))
        snack.show()
    }
}
