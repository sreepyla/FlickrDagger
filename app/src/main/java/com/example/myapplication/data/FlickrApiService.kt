package com.example.myapplication.data

import com.example.myapplication.model.FlickrResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApiService {
    @GET("feeds/photos_public.gne?format=json&nojsoncallback=1")
    suspend fun searchImages(@Query("tags") query: String): FlickrResponse
}
