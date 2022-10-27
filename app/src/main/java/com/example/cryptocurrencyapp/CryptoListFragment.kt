package com.example.cryptocurrencyapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cryptocurrencyapp.databinding.FragmentCryptoListBinding

class CryptoListFragment : Fragment() {
    private lateinit var binding: FragmentCryptoListBinding
    //private val cryptoCurrencyVm by
    //private val cryptoVM by activityViewModels<CryptoVM> { ViewModelFactory(cryptoUseCase) }

    /* private lateinit var binding: FragmentCryptoListBinding*/


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crypto_list, container, false)
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            CryptoListFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}