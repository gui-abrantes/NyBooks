package com.example.nybooks.presentation.di

import com.example.nybooks.presentation.books.BooksActivity
import com.example.nybooks.presentation.details.BookDetailsActivity
import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: BooksActivity)
    fun inject(activity: BookDetailsActivity)
}