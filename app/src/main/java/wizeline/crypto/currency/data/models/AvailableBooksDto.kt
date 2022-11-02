package wizeline.crypto.currency.data.models

import wizeline.crypto.currency.domain.model.AvailableBooksModel

data class AvailableBooksDto(
    val payload: List<PayloadBooksDto>,
    val success: Boolean
)

fun AvailableBooksDto.convertToListBooks(): List<AvailableBooksModel> {

    val listAvailablePayLoad = payload.mapIndexed { _, payload ->

        AvailableBooksModel(
            book = payload.book,
            minimumAmount = payload.minimumAmount,
            maximumAmount = payload.maximumAmount,
            minimumPrice = payload.minimumPrice,
            maximumPrice = payload.maximumPrice,
            minimumValue = payload.minimumValue

        )

    }
    return listAvailablePayLoad
}