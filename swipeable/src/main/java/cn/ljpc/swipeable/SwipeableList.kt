package cn.ljpc.swipeable

import android.util.Log
import androidx.compose.foundation.*
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Background(modifier: Modifier, btnLen: BtnLen) {
    Row(modifier = modifier
        .clickable {
            Log.d("#Background", "Row被点击了")
        }) {
        IconButton(
            onClick = {
                Log.d("#Background", "delete被点击了")
            },
            modifier = Modifier
                .background(color = Color.Red)
                .width(btnLen.width.dp)
                .combinedClickable {
                    Log.d("#Background", "delete被点击了")
                }
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                tint = MaterialTheme.colors.onPrimary,
                contentDescription = null
            )
        }
        IconButton(
            onClick = {
                Log.d("#Background", "people被点击了")
            },
            modifier = Modifier
                .background(color = Color.Blue)
                .width(btnLen.width.dp)
                .combinedClickable {
                    Log.d("#Background", "people被点击了")
                }
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
                thresholds = { _, _ -> FractionalThreshold(0.5f) },
                orientation = Orientation.Horizontal
            )
            .offset { IntOffset(state.offset.value.roundToInt(), 0) }
            .background(MaterialTheme.colors.background)
            .clickable {
                Log.d("#Foreground", "Foreground被点击了")
            }
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "this is $item"
        )
    }
}