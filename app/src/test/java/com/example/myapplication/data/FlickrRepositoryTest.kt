package com.example.myapplication.data

import com.example.myapplication.model.FlickrImage
import com.example.myapplication.model.FlickrResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.whenever

class FlickrRepositoryTest {

    private lateinit var repository: FlickrRepositoryImpl
    private lateinit var apiService: FlickrApiService

    @Before
    fun setup() {
        apiService = mock(FlickrApiService::class.java)
        repository = FlickrRepositoryImpl(apiService)
    }

    @Test
    fun `searchImages returns expected data`() = runBlocking {
        // Arrange
        val fakeResponse = FlickrResponse(
            items = listOf(
                FlickrImage("https://example.com/image1.jpg", "Image 1", "Author 1", "2025-01-01"),
                FlickrImage("https://example.com/image2.jpg", "Image 2", "Author 2", "2025-01-02")
            )
        )
        whenever(apiService.searchImages("test")).thenReturn(fakeResponse)

        // Act
        val result = repository.searchImages("test")

        // Assert
        assertEquals(2, result.size)
        assertEquals("Image 1", result[0].title)
    }

    @Test
    fun `searchImages handles API failure gracefully`() = runBlocking {
        // Arrange
        whenever(apiService.searchImages("test")).thenThrow(RuntimeException("Network error"))

        // Act
        val result = repository.searchImages("test")

        // Assert
        assertTrue(result.isEmpty())
    }
}
