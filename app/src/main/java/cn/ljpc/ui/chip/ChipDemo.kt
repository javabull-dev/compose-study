package cn.ljpc.ui.chip

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.ljpc.R
import cn.ljpc.ui.theme.ComposestudyTheme

@Composable
fun ChipDemo() {
    val selectedState = remember {
        mutableStateOf(false)
    }
    Row {
        CustomImageChip(
            selected = selectedState.value,
            text = "Jhon",
            modifier = Modifier.clickable {
                selectedState.value = !selectedState.value
            },
            imageId = R.drawable.p2
        )
    }
}

@Composable
fun CustomImageChip(
    text: String,
    imageId: Int,
    selected: Boolean,
    modifier: Modifier
) {
    Surface(
        color = when { //1.此处会设置字体的颜色
            selected -> MaterialTheme.colors.primary
            else -> Color.Transparent//透明
        },
        contentColor = when { //2.此处会设置字体的颜色，优先
            selected -> MaterialTheme.colors.onPrimary
            else -> Color.LightGray
        },
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = 1.dp,
            color = when {
                selected -> MaterialTheme.colors.primary
                else -> Color.LightGray
            }
        ),
        modifier = modifier
    ) {
        Row(modifier = modifier) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(20.dp)
                    .clip(CircleShape)
            )
            Text(
                text = text,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(end = 8.dp, top = 8.dp, bottom = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreViewChipDemo() {
    ComposestudyTheme {
        ChipDemo()
    }
}
