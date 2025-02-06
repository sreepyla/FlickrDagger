package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.FlickrRepository
import com.example.myapplication.model.FlickrImage
import kotlinx.coroutines.launch
import javax.inject.Inject

class FlickrImageViewModel @Inject constructor(
    private val flickrRepository: FlickrRepository
) : ViewModel() {

    var images: List<FlickrImage> = emptyList()
        private set

    fun searchImages(query: String) {
        viewModelScope.launch {
            images = flickrRepository.searchImages(query)
        }
    }
}
