package com.javg.cryptocurrencies.data.domain

import com.javg.cryptocurrencies.data.model.CRYBook
import com.javg.cryptocurrencies.data.repository.CRYBookRepository
import com.javg.cryptocurrencies.utils.getSecondCoinsText
import com.javg.cryptocurrencies.utils.separateStringCoins
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author Juan Vera Gomez
 * Date modified 22/02/2023
 *
 * Contains the necessary function to get the information
 * from books to the repository layer
 *
 * @param repository It is the repository that is responsible for obtaining the books
 *
 * @since 2.1
 */
class CRYBookUseCase @Inject constructor(
    private val repository: CRYBookRepository,
) {
    /**
     * Transform the list to assign the name of the simple book and add
     * the corresponding images to the main moment and the one to be converted
     *
     * @param books is the list a list of books
     * @return a transformed list
     */
    fun transformBooks(books: List<CRYBook>): List<CRYBook> {
        books.map {
            it.singleBook = it.book.separateStringCoins()
            it.imageUrl = "https://cryptoicons.org/api/icon/${it.book.separateStringCoins()}/200"
            it.bookDestination = "https://cryptoicons.org/api/icon/${it.book.getSecondCoinsText()}/200"
        }
        return books
    }

    /**
     * You are going to create a hashmap taking a list as a reference,
     * which will have the name of the coin as a key and a list
     * of all the coins that the main list has as a value to classify it.
     *
     * @param books is the list a list of books
     * @return a hashmap with the name of the coin as the key and the
     * list of all matches as the value
     */
    fun createUniqueMap(books: List<CRYBook>): HashMap<String, List<CRYBook>> {
        val mapBooks = HashMap<String, List<CRYBook>>()
        val uniqueBooks: List<CRYBook> = books
            .distinctBy { it.singleBook }
            .sortedBy { it.singleBook }

        uniqueBooks.forEach { uniqueBook ->
            val booksGroup = books.filter { it.singleBook == uniqueBook.singleBook }
            mapBooks[uniqueBook.singleBook] = booksGroup
        }

        return mapBooks
    }

    /**
     * Create a list distinguishing the first name of the book
     * and ordering it alphabetically
     *
     * @param books is the list a list of books
     * @return a list without repeated and ordered books
     */
    fun createListBookTitles(books: List<CRYBook>): List<CRYBook> {
        return books
            .distinctBy { it.singleBook }
            .sortedBy { it.singleBook }
    }

    /**
     * Gets the list of books that are stored in the database
     */
    fun queryBooks(): Flow<List<CRYBook>> = repository.queryBooks()

    /**
     * Query the api to get the books remotely
     */
    fun getAvailableBooksRx(): Boolean = repository.getAvailableBooksRx()
}
