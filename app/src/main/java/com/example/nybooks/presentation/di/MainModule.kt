package com.example.nybooks.presentation.di

import androidx.lifecycle.ViewModel
import com.example.nybooks.di.ViewModelKey
import com.example.nybooks.presentation.books.BooksViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(BooksViewModel::class)
    fun bindBooksViewModel(viewModel: BooksViewModel): ViewModel
}