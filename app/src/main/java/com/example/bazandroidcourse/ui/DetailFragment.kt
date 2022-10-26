package com.example.bazandroidcourse.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bazandroidcourse.data.utils.mappers.getCryptoName
import com.example.bazandroidcourse.databinding.FragmentDetailBinding
import com.example.bazandroidcourse.ui.utils.createURLImage
import com.example.bazandroidcourse.ui.utils.getTicker
import com.example.bazandroidcourse.ui.utils.getUnit
import com.squareup.picasso.Picasso

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var bookId:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val args = DetailFragmentArgs.fromBundle(it)
            bookId = args.bookId
            binding.apply{
                try{
                    Picasso.get()
                        .load(createURLImage(bookId))
                        .into(ivIcon)
                }catch (e:Exception){
                    e.printStackTrace()
                }
                tvCryptoName.text = bookId.getCryptoName()
                tvCryptoTicker.text = getTicker(bookId)
                val unit = getUnit(bookId)
                tvUpperUnit.text = unit
                tvLowerUnit.text = unit
                tvPriceUnit.text = unit
            }
        }
        Log.i("here","bookId $bookId")
    }

}