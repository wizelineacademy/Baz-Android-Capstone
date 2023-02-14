package com.javg.cryptocurrencies.data.domain

import com.javg.cryptocurrencies.data.model.CRYBook
import com.javg.cryptocurrencies.data.repository.CRYBookRepository
import com.javg.cryptocurrencies.ext.getSecondCoinsText
import com.javg.cryptocurrencies.ext.separateStringCoins
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @author Juan Vera Gomez
 * Date modified 10/02/2023
 *
 * Contains the necessary function to get the information
 * from books to the repository layer
 *
 * @param repository It is the repository that is responsible for obtaining the books
 *
 * @since 2.0
 */
class CRYBookUseCase @Inject constructor(
    private val repository: CRYBookRepository){

    /**
     * Is responsible for obtaining the list of books
     * delivered by the repository and at the same time
     * altering it to add the url of the image
     *
     * @param onRefresh flag that will indicate to the data layer if it
     * consults the remote information again according
     * to the user's interaction
     *
     */
    suspend operator fun invoke(onRefresh: Boolean): List<CRYBook> = withContext(Dispatchers.IO){
        val books = repository.getAvailableBooks(onRefresh)

        if (books.isEmpty())
            listOf<CRYBook>()
        else {
            books.map {
                it.imageUrl = "https://cryptoicons.org/api/icon/${it.book.separateStringCoins()}/200"
                it.bookDestination = "https://cryptoicons.org/api/icon/${it.book.getSecondCoinsText()}/200"
            }
        }
        books
    }

}