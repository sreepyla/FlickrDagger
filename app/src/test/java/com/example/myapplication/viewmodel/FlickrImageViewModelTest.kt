package com.example.myapplication.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myapplication.data.FlickrRepository
import com.example.myapplication.model.FlickrImage
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.*
import org.mockito.Mockito.*
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class FlickrImageViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testScheduler = TestCoroutineScheduler()
    private val testDispatcher = StandardTestDispatcher(testScheduler)
    private lateinit var viewModel: FlickrImageViewModel
    private lateinit var repository: FlickrRepository

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mock(FlickrRepository::class.java)
        viewModel = FlickrImageViewModel(repository)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `searchImages updates images list`() = runTest {
        // Arrange
        val dummyImages = listOf(
            FlickrImage("https://example.com/image1.jpg", "Image 1", "Author 1", "2025-01-01"),
            FlickrImage("https://example.com/image2.jpg", "Image 2", "Author 2", "2025-01-02")
        )
        whenever(repository.searchImages("test")).thenReturn(dummyImages)

        // Act
        viewModel.searchImages("test")
        testScheduler.advanceUntilIdle()

        // Assert
        assertEquals(2, viewModel.images.size)
        assertEquals(dummyImages, viewModel.images)
    }

    @Test
    fun `searchImages handles empty response`() = runTest {
        // Arrange
        whenever(repository.searchImages("empty")).thenReturn(emptyList())

        // Act
        viewModel.searchImages("empty")
        testScheduler.advanceUntilIdle()

        // Assert
        assertTrue(viewModel.images.isEmpty())
    }
}
