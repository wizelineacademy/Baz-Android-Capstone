package com.brendarojas.criptomonedaswizeline.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brendarojas.criptomonedaswizeline.databinding.FragmentCryptoDetailBinding

class CryptoDetailFragment : Fragment() {

    private var _bindingDetail: FragmentCryptoDetailBinding? = null
    private val bindingDetail get() = _bindingDetail!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingDetail = FragmentCryptoDetailBinding.inflate(inflater, container, false)
        val view = bindingDetail.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}