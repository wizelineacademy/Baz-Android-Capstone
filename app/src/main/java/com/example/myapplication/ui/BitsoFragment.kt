package com.example.myapplication.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.core.Resource
import com.example.myapplication.data.remote.BitsoDataSource
import com.example.myapplication.presentation.BitsoViewModel
import com.example.myapplication.presentation.BitsoViewModelFactory
import com.example.myapplication.repository.BitsoRepositoryImpl
import com.example.myapplication.repository.RetrofitClient


class BitsoFragment : Fragment(R.layout.activity_main) {

    private val viewModel by viewModels<BitsoViewModel> {
        BitsoViewModelFactory(
            BitsoRepositoryImpl(
            BitsoDataSource(RetrofitClient.webservice)
        )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchAvailiableBooks().observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {
                    Log.d("LiveData", "Loading...")
                }
                is Resource.Success -> {
                    Log.d("LiveData", "${result.data.toString()}")
                }
                is Resource.Failure -> {
                    Log.d("LiveData", "${result.exception}")
                }
            }

        })
    }
}
