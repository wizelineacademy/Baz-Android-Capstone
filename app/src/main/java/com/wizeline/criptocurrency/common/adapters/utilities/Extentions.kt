package com.wizeline.criptocurrency.common.adapters.utilities

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.wizeline.criptocurrency.common.Constants.DIAGONAL
import com.wizeline.criptocurrency.common.Constants.UNDERSCORE

fun Activity.toast(text:String,length:Int= Toast.LENGTH_SHORT){
    Toast.makeText(this,text,length).show()
}

fun Fragment.toast(text:String, length:Int= Toast.LENGTH_SHORT){
    Toast.makeText(requireContext(),text,length).show()
}

fun String?.toBookCodeFormat(): String =
    this?.replace(UNDERSCORE, DIAGONAL)?.uppercase().orEmpty()
