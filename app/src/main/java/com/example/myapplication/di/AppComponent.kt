package com.example.myapplication.di

import android.app.Application
import com.example.myapplication.MainActivity
import com.example.myapplication.viewmodel.FlickrImageViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(viewModel: FlickrImageViewModel)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}
