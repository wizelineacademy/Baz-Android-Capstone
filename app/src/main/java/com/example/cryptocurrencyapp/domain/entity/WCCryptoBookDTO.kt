package com.example.cryptocurrencyapp.domain.entity

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class WCCryptoBookDTO(
    val book: String = "",
    val minPrice: String = "",
    val maxPrice: String = "",
    val logo : Int = 0,
    val name: String = ""
):  Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt() ,
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(book)
        parcel.writeString(minPrice)
        parcel.writeString(maxPrice)
        parcel.writeInt(logo)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WCCryptoBookDTO> {
        override fun createFromParcel(parcel: Parcel): WCCryptoBookDTO {
            return WCCryptoBookDTO(parcel)
        }

        override fun newArray(size: Int): Array<WCCryptoBookDTO?> {
            return arrayOfNulls(size)
        }
    }
}
