package ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import taskrecorder.composeapp.generated.resources.Res
import taskrecorder.composeapp.generated.resources.ic_arrow_back

@Composable
fun TitleHeader(title: String, onBackPressed: (() -> Unit)? = null) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (onBackPressed != null) {
            Box(
                modifier = Modifier.size(44.dp)
                    .clip(CircleShape)
                    .clickable { onBackPressed() }
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(Res.drawable.ic_arrow_back),
                    contentDescription = "back",
                    modifier = Modifier.fillMaxSize()
                )
            }

        }
        Text(
            text = title,
            color = Color(0xFF333333),
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )
    }
}
