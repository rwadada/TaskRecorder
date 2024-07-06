package ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.*
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import taskrecorder.composeapp.generated.resources.Res
import taskrecorder.composeapp.generated.resources.ic_arrow_drop_down

@OptIn(FormatStringsInDatetimeFormats::class)
@Composable
@Preview
fun MainScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "TASK RECORDER",
            color = Color(0xFF333333),
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {}
                .border(
                    width = 1.dp,
                    color = Color(0xFFD9D9D9)
                )
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "sample",
                minLines = 1,
                color = Color(0xFF333333),
                modifier = Modifier.weight(1f)
            )
            Image(
                painter = painterResource(Res.drawable.ic_arrow_drop_down),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }

        Text(
            text = "CURRENT DATE TIME",
            fontSize = 13.sp,
            modifier = Modifier.padding(top = 24.dp),
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
        
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .clip(
                    shape = RoundedCornerShape(20.dp)
                )
                .background(color = Color(0xFF4169E1))
                .clickable {},
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "START",
                color = Color(0xFFFFFFFF),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {

        }
    }
}
