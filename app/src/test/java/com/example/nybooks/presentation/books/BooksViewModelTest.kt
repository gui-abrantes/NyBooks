package com.example.nybooks.presentation.books

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.nybooks.R
import com.example.nybooks.data.BooksResult
import com.example.nybooks.data.model.Book
import com.example.nybooks.data.repository.BooksRepository
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class) //inicializar o mock
class BooksViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var booksLiveDataObserver: Observer<List<Book>>

    @Mock
    private lateinit var viewFlipperLiveDataObserver: Observer<Pair<Int,Int?>>

    private lateinit var viewModel: BooksViewModel


    /*@Before //outra forma de inicializar o mock
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }*/

    @Test
    fun `when view model getBooks get success then set booksLiveData`() {
        //Arrange
        val books = listOf<Book>(
            Book("title 1", "author 1", "description 1")
        )

        val resultSuccess = MockRepository(BooksResult.Success(books))
        viewModel = BooksViewModel(resultSuccess)

        viewModel.booksLiveData.observeForever(booksLiveDataObserver)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        //Act
        viewModel.getBooks()

        //Assert
        verify(booksLiveDataObserver).onChanged(books)
        verify(viewFlipperLiveDataObserver).onChanged(Pair(1, null))
    }


    @Test
    fun `when view model getBooks get server error then set viewFlipperLiveData`() {
        // Arrange
        val resultServerError = MockRepository(BooksResult.ServerError)
        viewModel = BooksViewModel(resultServerError)

        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getBooks()

        // Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.books_error_500_generic))
    }

    @Test
    fun `when view model getBooks get api error 401 then set viewFlipperLiveData`() {
        // Arrange
        val resultApiError = MockRepository(BooksResult.ApiError(401))
        viewModel = BooksViewModel(resultApiError)

        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getBooks()

        // Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.books_error_401))
    }

    @Test
    fun `when view model getBooks get api error 400 then set viewFlipperLiveData`() {
        // Arrange
        val resultApiError = MockRepository(BooksResult.ApiError(410))
        viewModel = BooksViewModel(resultApiError)

        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        // Act
        viewModel.getBooks()

        // Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.books_error_400_generic))
    }

}

class MockRepository(private val result: BooksResult): BooksRepository {
    override fun getBooks(booksResultCallback: (result: BooksResult) -> Unit) {
        booksResultCallback(result)
    }

}