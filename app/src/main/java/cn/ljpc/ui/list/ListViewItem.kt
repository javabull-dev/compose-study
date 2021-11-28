package cn.ljpc.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
fun ListViewItem(item: Item, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = item.imageId),
            contentDescription = null,
            contentScale = ContentScale.Crop,//图片的缩放
            modifier = Modifier
                .fillMaxWidth()//设置最大宽度
                .clip(MaterialTheme.shapes.medium)//设置图片被包裹的形状
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = item.title, style = MaterialTheme.typography.h4)
        Text(text = item.subtitle, style = MaterialTheme.typography.subtitle1)
        Text(text = item.source, style = MaterialTheme.typography.h5)
    }
}

@Composable
@Preview(showBackground = true)
fun PreViewListViewItem() {
    ComposestudyTheme {
        ListViewItem(item = DataProvider.item)
    }
}