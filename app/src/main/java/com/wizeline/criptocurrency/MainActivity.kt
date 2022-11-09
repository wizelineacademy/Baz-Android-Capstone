package com.wizeline.criptocurrency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.wizeline.criptocurrency.common.adapters.utilities.toast
import com.wizeline.criptocurrency.ui.AvailableBooksFragment
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(AvailableBooksFragment())
    }

       fun loadFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_container_view, fragment)
        fragmentTransaction.commit()
    }


}



