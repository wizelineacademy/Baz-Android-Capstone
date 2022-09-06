package com.example.criptobitsoproyectwz.data.model

import com.google.gson.annotations.SerializedName

data class Fees(
    @SerializedName("flate_rate")val flat_rate : Flat_rate ,
    @SerializedName("structure")val structure : List<Structure>,


)
