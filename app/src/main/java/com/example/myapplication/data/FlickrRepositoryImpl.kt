package com.example.myapplication.data

import com.example.myapplication.model.FlickrImage
import javax.inject.Inject

class FlickrRepositoryImpl @Inject constructor(
    private val apiService: FlickrApiService
) : FlickrRepository {
    override suspend fun searchImages(query: String): List<FlickrImage> {
        return try {
            apiService.searchImages(query).items
        } catch (e: Exception) {
            emptyList() // Return empty list on API failure instead of throwing an error
        }
    }
}
