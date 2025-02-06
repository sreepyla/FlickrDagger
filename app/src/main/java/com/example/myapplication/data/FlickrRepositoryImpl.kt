package com.example.myapplication.data

import com.example.myapplication.model.FlickrImage
import javax.inject.Inject

class FlickrRepositoryImpl @Inject constructor(
    private val apiService: FlickrApiService
) : FlickrRepository {
    override suspend fun searchImages(query: String): List<FlickrImage> {
        return apiService.searchImages(query).items
    }
}
