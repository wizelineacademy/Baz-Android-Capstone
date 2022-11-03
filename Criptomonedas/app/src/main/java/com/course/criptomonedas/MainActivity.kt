package com.course.criptomonedas

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.course.criptomonedas.core.service
import com.course.criptomonedas.data.datasource.RemoteDataSourceImpl
import com.course.criptomonedas.data.models.Payload
import com.course.criptomonedas.data.repository.AvailableBooksRepositoryImpl
import com.course.criptomonedas.databinding.ActivityMainBinding
import com.course.criptomonedas.ui.AdapterBooks
import com.course.criptomonedas.ui.AvailableBooksViewModel
import com.course.criptomonedas.ui.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val TAG = "Criptos"
    private lateinit var adapterAvBooks: AdapterBooks
    private lateinit var mRecyclerView: RecyclerView

    private val viewModel: AvailableBooksViewModel by viewModels {
        MainViewModelFactory(
            AvailableBooksRepositoryImpl(
                RemoteDataSourceImpl(
                    service
                )
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        configAdapter()
        viewModel.getAvailableBooks()

        viewModel.availableBooks.observe(this) {
            adapterAvBooks.submitList(it.payload)
            Log.d(TAG, "onCreate: Frank ${it.payload}")
        }
    }


    private fun configAdapter() {
        mRecyclerView = binding.rvListBook
        val listOfBooks: List<Payload> = emptyList()
        adapterAvBooks = AdapterBooks {
            Log.d(TAG, "configAdapter: ${it.book}")
        }
        mRecyclerView.adapter = adapterAvBooks
        adapterAvBooks.submitList(listOfBooks)
    }
}