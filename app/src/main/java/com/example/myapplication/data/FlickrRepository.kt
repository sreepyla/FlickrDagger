package com.example.myapplication.data

import com.example.myapplication.model.FlickrImage

interface FlickrRepository {
    suspend fun searchImages(query: String): List<FlickrImage>
}
