package com.rwadada.taskrecorder

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import data.repository.RoutingRepository
import org.koin.java.KoinJavaComponent.inject

class MainActivity : ComponentActivity() {
    private val repository: RoutingRepository by inject(RoutingRepository::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BackHandler{
                repository.popBackStack()
            }
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}