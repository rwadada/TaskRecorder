package ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.*
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.common.DropDownButton
import ui.common.NormalButton
import ui.common.SubTitle
import ui.common.TitleHeader

@OptIn(FormatStringsInDatetimeFormats::class)
@Composable
@Preview
fun MainScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
    ) {
        TitleHeader(title = "TASK RECORDER")
        Spacer(modifier = Modifier.height(16.dp))
        DropDownButton(
            selectedItemString = "sample",
            onClick = {}
        )

        SubTitle(
            subTitle = "CURRENT DATE TIME",
            modifier = Modifier.padding(top = 24.dp)
        )

        val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val format = LocalDateTime.Format {
            byUnicodePattern("yyyy/MM/dd HH:mm")
        }
        val displayDateTimeString = today.format(format)
        Text(
            text = displayDateTimeString,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 12.dp),
        )

        NormalButton(
            text = "START",
            modifier = Modifier.fillMaxWidth(),
            onClick = {}
        )

        Column(
            modifier = Modifier.fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {

        }
    }
}
