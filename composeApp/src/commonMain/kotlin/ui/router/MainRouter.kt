package ui.router

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import data.repository.Routing
import finishApp
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import viewmodels.MainRouterViewModel

@OptIn(KoinExperimentalAPI::class)
@Composable
fun MainRouter() {
    val viewModel = koinViewModel<MainRouterViewModel>()
    val currentPosition by viewModel.navigationEvent.collectAsState(Routing.Home)

    when(currentPosition) {
        Routing.Home -> {

        }

        Routing.SelectTask -> {

        }

        null -> {
            finishApp()
        }
    }
}