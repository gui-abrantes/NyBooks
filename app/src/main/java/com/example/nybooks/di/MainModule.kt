package com.example.nybooks.di

import com.example.nybooks.data.repository.BooksApiDataSource
import com.example.nybooks.data.repository.BooksRepository
import com.example.nybooks.presentation.books.BooksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    factory<BooksRepository> {
        BooksApiDataSource()
    }

    viewModel {
        BooksViewModel(
            dataSource = get()
        )
    }
}