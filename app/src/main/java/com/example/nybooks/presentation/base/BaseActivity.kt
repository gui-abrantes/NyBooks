package com.example.nybooks.presentation.base

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

open class BaseActivity: AppCompatActivity() {
    protected  fun setupToolBar(toolbar: Toolbar, titleIdRes: Int, showBackButton: Boolean = false) {
        toolbar.title = getString(titleIdRes)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(showBackButton)
    }
}