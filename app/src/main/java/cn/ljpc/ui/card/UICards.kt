package cn.ljpc.ui.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.ljpc.R
import cn.ljpc.ui.data.DataProvider

@Composable
fun UICards() {
    val item by remember {
        mutableStateOf(DataProvider.item)
    }
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.primary,
        shape = RoundedCornerShape(topStart = 16.dp, bottomEnd = 16.dp)
    ) {
        Column {
            Text(
                text = item.title,
                modifier = Modifier.padding(8.dp),
                color = MaterialTheme.colors.onPrimary
            )
            Text(
                text = item.subtitle,
                modifier = Modifier.padding(8.dp),
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
    Divider()
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        elevation = 4.dp
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.p7),
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
            Text(text = item.title, modifier = Modifier.padding(16.dp))
        }
    }
    //分割线
    Divider()
}

@Composable
@Preview(showBackground = true)
fun ShowUICards(){
    Column {
        UICards()
    }
}