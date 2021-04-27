package com.example.nybooks.data.repository

import android.content.Context
import com.example.nybooks.data.ApiService
import com.example.nybooks.data.BooksResult
import com.example.nybooks.data.model.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class BooksApiDataSource @Inject constructor() : BooksRepository {

    override suspend fun getBooks(): BooksResult {
        return withContext(Dispatchers.Default) {
            lateinit var bookResult: BooksResult

            try {
                val response = ApiService.service.getBooks().execute()
                when {
                    response.isSuccessful -> {
                        val books: MutableList<Book> = mutableListOf()

                        response.body()?.let {bookBodyResponse ->
                            for (result in bookBodyResponse.bookResults) {
                                val book = result.bookDetails[0].getBookModel()

                                books.add(book)
                            }
                        }

                        bookResult = BooksResult.Success(books)
                    }
                    else -> bookResult = BooksResult.ApiError(response.code())
                }

            } catch (e: Exception) {
                bookResult = BooksResult.ServerError
            }

            bookResult
        }
    }
}