package wizeline.crypto.currency.domain.model

data class AvailableBooksModel(
    val book: String,
    val minimumAmount: String,
    val maximumAmount: String,
    val minimumPrice:  String,
    val maximumPrice:  String,
    val minimumValue:  String,
)
