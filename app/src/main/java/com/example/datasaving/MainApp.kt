package com.example.datasaving

import android.app.Application

class MainApp : Application() {

    lateinit var viewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()
        viewModel = MainViewModel(Repository(this), SecondsToHours.Base())
    }
}