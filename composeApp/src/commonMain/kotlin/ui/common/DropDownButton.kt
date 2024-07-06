package ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import taskrecorder.composeapp.generated.resources.Res
import taskrecorder.composeapp.generated.resources.ic_arrow_drop_down

@Composable
fun DropDownButton(
    selectedItemString: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .border(
                width = 1.dp,
                color = Color(0xFFD9D9D9)
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = selectedItemString,
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
}