package wizeline.crypto.currency.ui.homeCurrency

import wizeline.crypto.currency.domain.model.AvailableBooksModel

data class HomeState(
    val book: List<AvailableBooksModel> = emptyList(),
    val isLoading: Boolean = false
)