package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myapplication.presentation.FlickrImageSearchScreen
import com.example.myapplication.viewmodel.FlickrImageViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModel: FlickrImageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContent {
            FlickrImageSearchScreen(viewModel = viewModel)
        }
    }
}
