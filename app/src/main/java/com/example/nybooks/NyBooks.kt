package com.example.nybooks

import android.app.Application
import com.example.nybooks.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NyBooks: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@NyBooks)

            modules(mainModule)
        }
    }
}