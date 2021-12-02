package cn.ljpc.swipeable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt


enum class SwipeState {
    SHOW_ACTION, NONE
}

@Composable
@Preview(showBackground = true)
fun SwipeableList() {
    val listState = remember {
        mutableListOf(1, 2, 3, 4, 5, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18)
    }

    val btnLen = BtnLen(40f, 2, LocalDensity.current.density)
    LazyColumn {
        items(listState) { item ->
            Box {
                Background(
                    btnLen = btnLen,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                )
                Foreground(item, btnLen = btnLen)
            }
        }
    }
}

@Composable
fun Background(modifier: Modifier, btnLen: BtnLen) {
    Row(horizontalArrangement = Arrangement.End, modifier = modifier) {
        IconButton(
            onClick = {},
            modifier = Modifier
                .background(color = Color.Red)
                .width(btnLen.width.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                tint = MaterialTheme.colors.onPrimary,
                contentDescription = null
            )
        }
        IconButton(
            onClick = {},
            modifier = Modifier
                .background(color = Color.Blue)
                .width(btnLen.width.dp)
        ) {
            Icon(
                imageVector = Icons.Default.AccountBox,
                tint = MaterialTheme.colors.onPrimary,
                contentDescription = null
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Foreground(item: Int, btnLen: BtnLen) {
    val state =
        rememberSwipeableState(
            initialValue = SwipeState.NONE,
            confirmStateChange = { true })

    val swipeAnchors =
        mapOf(
            0f to SwipeState.NONE,
            -btnLen.length() to SwipeState.SHOW_ACTION,
        )
    Card(
        border = BorderStroke(2.dp, Color.Gray),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .swipeable(//控制是否可被滑动，提供state给外部使用
                state = state,
                anchors = swipeAnchors,
                thresholds = { _, _ -> FractionalThreshold(0.5f) },//可用可不用
                orientation = Orientation.Horizontal
            )
            .offset { IntOffset(state.offset.value.roundToInt(), 0) }//是否可看到background，取决于offset偏移量
            .background(MaterialTheme.colors.background)
    ) {
        Text("this is $item")
    }
}