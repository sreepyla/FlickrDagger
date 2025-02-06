package com.example.myapplication.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.model.FlickrImage
import com.example.myapplication.viewmodel.FlickrImageViewModel

@Composable
fun FlickrImageSearchScreen(viewModel: FlickrImageViewModel) {
    var searchText by remember { mutableStateOf("") }
    var selectedImage by remember { mutableStateOf<FlickrImage?>(null) }

    if (selectedImage != null) {
        ImageDetailScreen(
            image = selectedImage!!,
            onBack = { selectedImage = null }
        )
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                placeholder = { Text("Search images...") }
            )

            Button(
                onClick = { viewModel.searchImages(searchText) },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Search")
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize()
            ) {
                items(viewModel.images.size) { index ->
                    val image = viewModel.images[index]
                    Image(
                        painter = rememberAsyncImagePainter(image.imageUrl),
                        contentDescription = image.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .padding(8.dp)
                            .clickable { selectedImage = image }
                    )
                }
            }
        }
    }
}
