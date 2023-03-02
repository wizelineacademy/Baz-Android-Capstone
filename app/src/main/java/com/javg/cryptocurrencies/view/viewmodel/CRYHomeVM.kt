package com.javg.cryptocurrencies.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.javg.cryptocurrencies.R
import com.javg.cryptocurrencies.data.domain.CRYBookUseCase
import com.javg.cryptocurrencies.data.model.CRYBook
import com.javg.cryptocurrencies.utils.CRYUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Juan Vera Gomez
 * Date modified 22/02/2023
 *
 * Contains the necessary functions to obtain the information
 * from the cryptocurrency books
 *
 * @since 1.3
 */
@HiltViewModel
class CRYHomeVM @Inject constructor(
    application: Application,
    private val bookUseCase: CRYBookUseCase,
) : AndroidViewModel(application) {

    private var _booksMap = MutableLiveData<HashMap<String, List<CRYBook>>>()
    private var _books = MutableLiveData<List<CRYBook>>()
    private var _updateTime = MutableLiveData<String>()
    private var _chipsTitles = MutableLiveData<List<CRYBook>>()
    private var _equalBooks = MutableLiveData<List<CRYBook>>()

    val updateTime: LiveData<String>
        get() = _updateTime

    val chipsTitles: LiveData<List<CRYBook>>
        get() = _chipsTitles

    val equalBooks: LiveData<List<CRYBook>>
        get() = _equalBooks

    init {
        _updateTime.value = getTimeUpdate()
        queryBookFlow()
    }

    /**
     * It is responsible for requesting the list of books from the data
     * layer which can be called with user interaction.
     *
     * @param onRefresh flag that will indicate to the data layer
     * if it consults the remote information again
     *
     */
    fun getBooks(onRefresh: Boolean = false) {
        viewModelScope.launch {
            /**
             * List with all books
             */
            _books.value = bookUseCase.invoke(onRefresh)

            if (onRefresh && _books.value?.isNotEmpty()!!) {
                _updateTime.value = getTimeUpdate()
            } else {
                _updateTime.value = getTimeUpdate()
            }
        }
    }

    /**
     * Updates the view list with another list according to the
     * position selected by the user
     *
     * @param position is the position of the user selection
     */
    fun updateListDifferentBook(position: Int) {
        val book = _chipsTitles.value?.get(position)
        _equalBooks.value = _booksMap.value?.get(book?.singleBook)
    }

    /**
     * Returns a composite legend with the updated time of the remote service query
     */
    private fun getTimeUpdate(): String = String.format(getApplication<Application>().applicationContext.getString(R.string.cry_update_day), CRYUtils.getSaveTime(getApplication<Application>().applicationContext))

    private fun queryBookFlow() {
        viewModelScope.launch {
            bookUseCase.queryBooks().collect {
                if (it.isNotEmpty()) {
                    _books.value = bookUseCase.transformBooks(it)
                    _chipsTitles.value = bookUseCase.createListBookTitles(_books.value!!)
                    _booksMap.value = bookUseCase.createUniqueMap(_books.value!!)
                    _equalBooks.value = _booksMap.value?.get(_chipsTitles.value?.firstOrNull()?.singleBook)
                }
            }
        }
    }
}
