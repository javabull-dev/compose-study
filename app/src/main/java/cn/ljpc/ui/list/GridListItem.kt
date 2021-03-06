package cn.ljpc.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.ljpc.ui.data.DataProvider
import cn.ljpc.ui.data.Item
import cn.ljpc.ui.theme.ComposestudyTheme

@Composable
fun GridListItem(
    item: Item,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .width(190.dp)
            .height(220.dp)
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.clickable(onClick = { })) {
            Image(
                painter = painterResource(item.imageId),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth()
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.h6.copy(fontSize = 14.sp),
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = item.subtitle,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body2
                )
                Text(
                    text = item.source,
                    style = MaterialTheme.typography.subtitle2
                )

            }
        }
    }
}

@Preview
@Composable
fun PreviewGridListItem() {
    ComposestudyTheme {
        GridListItem(item = DataProvider.item)
    }
}