package wizeline.crypto.currency.data.models

import com.google.gson.annotations.SerializedName

data class PayloadBooksDto (
    val book: String,
    @SerializedName("minimum_amount")
    val minimumAmount: String,
    @SerializedName("maximum_amount")
    val maximumAmount: String,
    @SerializedName("minimum_price")
    val minimumPrice:  String,
    @SerializedName("maximum_price")
    val maximumPrice:  String,
    @SerializedName("minimum_value")
    val minimumValue:  String,
)