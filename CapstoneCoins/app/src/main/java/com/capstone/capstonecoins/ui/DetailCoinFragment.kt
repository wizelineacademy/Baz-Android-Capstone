package com.capstone.capstonecoins.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.capstone.capstonecoins.R
import com.capstone.capstonecoins.data.models.availablebooks.BooksDto
import com.capstone.capstonecoins.data.models.availablebooks.Payload
import com.capstone.capstonecoins.databinding.FragmentDetailCoinBinding

class DetailCoinFragment : Fragment() {

    private var _binding: FragmentDetailCoinBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailCoinBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            var args = it.getSerializable("Books")
            Log.d("Mensaje", "Args: $args")
            if (args is BooksDto) {
                compares(args)
            }
        }

    }

    private fun compares(args: BooksDto) {

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}