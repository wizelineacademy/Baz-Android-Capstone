package com.example.myapplication.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentBitsoDetailsBinding

class BitsoDetailsFragment : Fragment(R.layout.fragment_bitso_details) {

    private lateinit var binding: FragmentBitsoDetailsBinding
    private val args by navArgs<BitsoDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBitsoDetailsBinding.bind(view)

        Glide.with(requireContext()).load(R.drawable.syntetix).centerCrop().into(binding.imgBitcoin)
        Glide.with(requireContext()).load(R.drawable.bitcoinfondo).centerCrop().into(binding.imgBackground)
        binding.txtBitsoTitle.text = args.bitcoinTitle.uppercase()
        binding.minPrice.text = args.minPrice
        binding.maxPrice.text = args.maxPrice
        binding.maxAmount.text = args.maxAmount
        binding.minAmount.text = args.minAmount
    }
}
