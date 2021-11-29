package cn.ljpc.ui.carousellayout

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CarouselDot(selected: Boolean, color: Color, icon: ImageVector) {
    Icon(
        imageVector = icon,
        modifier = Modifier
            .padding(4.dp)
            .size(12.dp),
        contentDescription = null,
        tint = if (selected) color else Color.Gray //着色
    )
}

@Preview
@Composable
fun PreviewPaginationPionters() {
    Row(modifier = Modifier.size(100.dp)) {
        CarouselDot(
            true,
            MaterialTheme.colors.primary,
            Icons.Filled.Send
        )
        CarouselDot(
            false,
            MaterialTheme.colors.primary,
            Icons.Filled.Favorite
        )
        CarouselDot(
            false,
            MaterialTheme.colors.error,
            Icons.Filled.AccountBox
        )
    }
}
