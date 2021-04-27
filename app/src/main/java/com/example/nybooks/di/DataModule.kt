package com.example.nybooks.di

import com.example.nybooks.data.repository.BooksApiDataSource
import com.example.nybooks.data.repository.BooksRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataModule{

    @Singleton
    @Binds
    abstract fun providerBooksDataSource(repository: BooksApiDataSource): BooksRepository
}