package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.AppComponent
import com.example.myapplication.di.DaggerAppComponent

class MyApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this) // This should resolve after rebuild
    }
}
