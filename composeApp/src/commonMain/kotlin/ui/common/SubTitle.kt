package ui.common

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun SubTitle(
    subTitle: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = subTitle,
        fontSize = 13.sp,
        modifier = modifier
    )
}