package cn.ljpc.ui.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.ljpc.ui.data.DataProvider
import cn.ljpc.ui.theme.ComposestudyTheme

@Composable
fun VerticalListView() {
    val list = remember {
        DataProvider.itemList
    }

    LazyColumn {
        items(
            items = list,
            itemContent = { item ->
                if ((item.id % 3) == 0) {
                    VerticalListItemSmall(item = item)
                } else {
                    ListViewItem(item = item)
                }
                ListItemDivider()
            }
        )
    }
}

@Composable
private fun ListItemDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp),
        color = MaterialTheme.colors.background
    )
}

@Composable
fun HorizontalListView() {
    val list = remember { DataProvider.itemList }
    //verticalScroll 垂直滚动
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Good Food",
            style = MaterialTheme.typography.body2
        )
        LazyRow(
            modifier = Modifier.padding(end = 16.dp)
        ) {
            items(
                items = list,
                itemContent = {
                    HorizontalListItem(
                        it,
                        Modifier.padding(start = 16.dp, bottom = 16.dp)
                    )
                })
        }
        ListItemDivider()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridListView() {
    val list = remember {
        DataProvider.itemList
    }
    Column {
        LazyVerticalGrid(cells = GridCells.Fixed(2)) {
            items(list) { item ->
                GridListItem(item = item)
            }
        }
    }
}

//@Composable
//@Preview(showBackground = true)
//fun PreviewVerticalListView() {
//    ComposestudyTheme {
//        VerticalListView()
//    }
//}

//@Composable
//@Preview(showBackground = true)
//fun PreviewGridListView() {
//    ComposestudyTheme {
//        GridListView()
//    }
//}