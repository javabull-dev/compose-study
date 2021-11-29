package cn.ljpc.ui.appbar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.ljpc.ui.theme.ComposestudyTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//定义一个协程作用域
//private val coroutineScope = CoroutineScope(CoroutineName("foo"))
//rememberCoroutineScope 通过这种方式创建

@Composable
fun SnackBars() {
    //默认的颜色是黑色的，形状是圆角
    val clicked = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(clicked) {
        withContext(Dispatchers.IO) {

        }
    }

    Snackbar(
        modifier = Modifier.padding(4.dp),
        action = {
            TextButton(onClick = { clicked.value = true }) {
                Text(text = "click")
            }
        }) {
        Text(text = "This is a basic snackbar")
    }

    Snackbar(
        modifier = Modifier.padding(4.dp),
        actionOnNewLine = true,
        action = {
            TextButton(onClick = {}) {
                Text(text = "Remove")
            }
        }
    ) {
        Text(text = "Snackbar with action item below text")
    }
}

@Preview(showBackground = true)
@Composable
fun ShowSnackbars() {
    ComposestudyTheme {
        Column {
            SnackBars()
        }
    }
}
