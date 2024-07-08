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
import ui.common.DropDownButton
import ui.common.NormalButton
import ui.common.SubTitle
import ui.common.TitleHeader
import viewmodels.MainUiEvent
import viewmodels.MainViewModel
import viewmodels.UiState

@Composable
@Preview
fun MainScreen() {
    val viewModel: MainViewModel = MainViewModel.getInstance()
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
        DropDownButton(
            selectedItemString = "sample",
            onClick = uiEventHandler::onClickTaskDropDown
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
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(uiState.workHistryList) {
                HistoryItem(it)
            }
        }
    }
}
