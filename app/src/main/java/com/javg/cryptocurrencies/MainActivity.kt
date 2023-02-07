package com.javg.cryptocurrencies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.javg.cryptocurrencies.ui.book.CRYBookFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Cryptocurrencies)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cry_activity)

        val fm = supportFragmentManager.beginTransaction()
        fm.replace(R.id.root_layout, CRYBookFragment())
        fm.addToBackStack(null)
        fm.commit()
    }

    override fun onBackPressed() {
        var canBack = false
        val fragment = supportFragmentManager.findFragmentById(R.id.root_layout)

        if(fragment is CRYBookFragment)
            canBack = true

        if(canBack)
            finish()
        else
            super.onBackPressed()
    }
}
