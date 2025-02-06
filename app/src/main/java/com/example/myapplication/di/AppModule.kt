package com.example.myapplication.di

import com.example.myapplication.data.FlickrApiService
import com.example.myapplication.data.FlickrRepository
import com.example.myapplication.data.FlickrRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindFlickrRepository(flickrRepositoryImpl: FlickrRepositoryImpl): FlickrRepository

    companion object {
        private const val BASE_URL = "https://api.flickr.com/services/"

        @Provides
        @Singleton
        fun provideRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Provides
        @Singleton
        fun provideFlickrApiService(retrofit: Retrofit): FlickrApiService {
            return retrofit.create(FlickrApiService::class.java)
        }
    }
}
