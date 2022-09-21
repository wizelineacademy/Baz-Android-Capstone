package com.brendarojas.criptomonedaswizeline.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brendarojas.criptomonedaswizeline.R
import com.brendarojas.criptomonedaswizeline.databinding.FragmentCryptoListBinding

class CryptoListFragment : Fragment() {

    private var _bindingList: FragmentCryptoListBinding? = null
    private val bindingList get() = _bindingList!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingList = FragmentCryptoListBinding.inflate(inflater, container, false)
        val view = bindingList.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}