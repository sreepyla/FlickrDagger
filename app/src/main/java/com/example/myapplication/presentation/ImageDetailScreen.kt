package com.example.myapplication.presentation

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.model.FlickrImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageDetailScreen(image: FlickrImage, onBack: () -> Unit) {
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = { Text("Image Details") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = rememberAsyncImagePainter(image.imageUrl),
            contentDescription = image.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("Title: ${image.title}", modifier = Modifier.padding(8.dp))
        Text("Author: ${image.author}", modifier = Modifier.padding(8.dp))
        Text("Published: ${image.publishedDate}", modifier = Modifier.padding(8.dp))

        Button(
            onClick = {
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Check out this image: ${image.imageUrl}")
                    type = "text/plain"
                }
                context.startActivity(Intent.createChooser(intent, "Share Image"))
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Share Image")
        }
    }
}
