package ui.router

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import data.repository.Routing
import finishApp
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import ui.main.MainScreen
import viewmodels.MainRouterViewModel

@OptIn(KoinExperimentalAPI::class)
@Composable
fun MainRouter() {
    val viewModel = koinViewModel<MainRouterViewModel>()
    val currentPosition by viewModel.navigationEvent.collectAsState(Routing.Home)
    Box(modifier = Modifier.fillMaxSize()) {
        Crossfade(currentPosition) {
            when(it) {
                Routing.Home -> {
                    MainScreen()
                }
                Routing.SelectTask -> {
                    Box(modifier = Modifier.fillMaxSize().background(Color.Blue))
                }
                null -> {
                    finishApp()
                    viewModel.onFinish()
                }
            }
        }
    }
}