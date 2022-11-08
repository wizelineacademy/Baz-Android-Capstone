package com.example.myapplication.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentBitsoDetailsBinding

class BitsoDetailsFragment : Fragment(R.layout.fragment_bitso_details) {

    private lateinit var binding: FragmentBitsoDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBitsoDetailsBinding.bind(view)
    }
}