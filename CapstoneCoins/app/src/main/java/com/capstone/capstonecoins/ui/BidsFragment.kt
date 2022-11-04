package com.capstone.capstonecoins.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.capstone.capstonecoins.data.repository.DetailCoinRepositoryImpl
import com.capstone.capstonecoins.data.retrofit
import com.capstone.capstonecoins.data.utils.COIN_KEY
import com.capstone.capstonecoins.databinding.FragmentBidsBinding
import com.capstone.capstonecoins.domain.api.usecases.DetailCoinUseCase
import com.capstone.capstonecoins.ui.viewmodels.BidsViewmodel
import com.capstone.capstonecoins.ui.viewmodels.ViewModelFactorym


class BidsFragment : Fragment() {

    private var _binding: FragmentBidsBinding? = null
    private val binding get() = _binding!!

    private val bidsViewmodel: BidsViewmodel by viewModels {
        ViewModelFactorym(DetailCoinUseCase(DetailCoinRepositoryImpl(retrofit)))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            var typeCoin = it.getString(COIN_KEY)
            Log.d("Mensaje", "type: $typeCoin")
            callServices()
            attachObservers()
        }
    }

    private fun attachObservers() {
        bidsViewmodel.bidsCoin.observe(viewLifecycleOwner) {

        }
    }

    private fun callServices() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBidsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

}