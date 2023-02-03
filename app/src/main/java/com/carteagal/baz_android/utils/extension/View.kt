package com.carteagal.baz_android.utils.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.carteagal.baz_android.R

fun ImageView.loadImage(url: String){
    Glide
        .with(context)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.oval_white)
        .error(R.drawable.bitcoin)
        .into(this)
}