package cn.ljpc.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.ljpc.ui.data.DataProvider
import cn.ljpc.ui.data.Item
import cn.ljpc.ui.theme.ComposestudyTheme

@Composable
fun VerticalListItemSmall(item: Item, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
//            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = {})
    ) {
        Image(
            painter = painterResource(id = item.imageId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(end = 16.dp)
                .size(100.dp, 80.dp)
                .clip(MaterialTheme.shapes.medium)
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(text = item.title, style = MaterialTheme.typography.h6)
            Text(text = item.subtitle, style = MaterialTheme.typography.body2)
        }
        FavIcon()
    }
}

@Composable
fun FavIcon(modifier: Modifier = Modifier) {
    val isClick = remember {
        mutableStateOf(true)
    }
    IconToggleButton(
        checked = isClick.value,
        onCheckedChange = { isClick.value = !isClick.value }) {
        val imageVec = if (isClick.value) {
            Icons.Filled.Favorite
        } else {
            Icons.Default.FavoriteBorder
        }
        Icon(
            imageVector = imageVec,
            contentDescription = null,
            modifier = modifier
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewVerticalListItemSmall() {
    ComposestudyTheme {
        VerticalListItemSmall(item = DataProvider.item)
    }
}