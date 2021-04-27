package com.example.nybooks

import android.app.Application
import com.example.nybooks.di.ApplicationComponent
import com.example.nybooks.di.DaggerApplicationComponent

class NyBooks : Application() {
    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent.factory().create(this)

    }
}