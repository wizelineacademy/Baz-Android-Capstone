package wizeline.crypto.currency.ui.homeCurrency.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import wizeline.crypto.currency.domain.useCase.AvailableBookUseCase
import wizeline.crypto.currency.ui.homeCurrency.HomeState
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import wizeline.crypto.currency.data.Result

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val availableBookUseCase: AvailableBookUseCase
) : ViewModel() {
    val state = MutableLiveData(HomeState(isLoading = true))

    init {}

     fun getAvailableBook() {
        viewModelScope.launch {

            availableBookUseCase().onEach { result ->
             when (result) {
                    is Result.Success -> {
                        state.value= state.value?.copy(
                                book = result.data ?: emptyList(),
                                isLoading = false)
                    }
                    is Result.Error -> {
                        state.value= state.value?.copy(
                                isLoading = false
                        )
                    }
                    is Result.Loading->{
                        state.value= state.value?.copy(
                                isLoading = true
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }


}