package ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import ui.common.*
import viewmodels.MainUiEvent
import viewmodels.MainViewModel
import viewmodels.UiState

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun MainScreen() {
    val viewModel: MainViewModel = koinViewModel<MainViewModel>()
    val uiEventHandler: MainUiEvent = viewModel
    val uiState: UiState by viewModel.uiState.collectAsState()

    var shouldCollectLocalDateTime: Boolean

    LaunchedEffect(Unit) {
        shouldCollectLocalDateTime = true
        while (shouldCollectLocalDateTime) {
            uiEventHandler.onLoadCurrentDateTime()
            delay(1000)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            shouldCollectLocalDateTime = false
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
    ) {
        TitleHeader(title = "TASK RECORDER")
        Spacer(modifier = Modifier.height(16.dp))
        TmpDropDownButton(
            text = uiState.workTitle,
            onValueChange = uiEventHandler::onWorkTitleChange
        )

        SubTitle(
            subTitle = "CURRENT DATE TIME",
            modifier = Modifier.padding(top = 24.dp)
        )

        Text(
            text = uiState.currentDateTimeString,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 12.dp),
        )

        NormalButton(
            text = if (uiState.inProgress) {
                "IN PROGRESS - (${uiState.elapsedTimeString})"
            } else {
                "START"
            },
            modifier = Modifier.fillMaxWidth(),
            onClick = uiEventHandler::onClickStartOrStop
        )

        if (uiState.workHistryList.isNotEmpty()) {
            SubTitle(
                subTitle = "HISTORY",
                modifier = Modifier.padding(top = 24.dp)
            )

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(uiState.workHistryList) {
                    HistoryItem(it)
                }
                item {
                    SubButton(
                        text = "CLEAR",
                        modifier = Modifier.fillMaxWidth(),
                        onClick = uiEventHandler::onClickClear
                    )
                }
            }
        }
    }
}
