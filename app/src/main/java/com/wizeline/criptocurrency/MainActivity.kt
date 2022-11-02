package com.wizeline.criptocurrency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wizeline.criptocurrency.common.adapters.RetrofitClient
import com.wizeline.criptocurrency.data.repository.BitsoRepository
import com.wizeline.criptocurrency.data.repository.BitsoRepositoryImp
import com.wizeline.criptocurrency.domain.model.use_case.AvailableBooksUseCase
import com.wizeline.criptocurrency.domain.model.use_case.OrderBookUseCase
import com.wizeline.criptocurrency.domain.model.use_case.TickerUseCase
import com.wizeline.criptocurrency.ui.AvailableBooksFragment
import com.wizeline.criptocurrency.ui.CriptoCurrencyViewModel

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



