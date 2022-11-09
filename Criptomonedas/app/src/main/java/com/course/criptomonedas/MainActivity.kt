package com.course.criptomonedas

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.course.criptomonedas.core.service
import com.course.criptomonedas.data.datasource.availablebooks.RemoteDataSourceImpl
import com.course.criptomonedas.data.repository.AvailableBooksRepositoryImpl
import com.course.criptomonedas.databinding.ActivityMainBinding
import com.course.criptomonedas.domain.GetAvailableBooksCase
import com.course.criptomonedas.ui.adapter.AdapterBooks
import com.course.criptomonedas.ui.AvailableBooksViewModel
import com.course.criptomonedas.ui.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val TAG = "Criptos"
    private lateinit var adapterAvBooks: AdapterBooks
    private lateinit var mRecyclerView: RecyclerView

    private val viewModel: AvailableBooksViewModel by viewModels {
        MainViewModelFactory(
            GetAvailableBooksCase(
                AvailableBooksRepositoryImpl(
                    RemoteDataSourceImpl(
                        service
                    )
                )
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)


    }



}