package cn.ljpc.gmail.ui.animate

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.ljpc.gmail.ui.theme.ComposestudyTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@Composable
fun AnimateButton() {
    var buttonState by remember {
        mutableStateOf(false)
    }

    Box(modifier = Modifier, contentAlignment = Alignment.Center) {
        IconButton(
            onClick = { buttonState = !buttonState },
            modifier = Modifier
                .size(100.dp, 50.dp)
                .border(2.dp, Color.Yellow, RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .background(color = MaterialTheme.colors.secondary)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(imageVector = Icons.Outlined.Send, contentDescription = null)
                Text(text = "send")
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SlideInAndOut() {
    val count = remember {
        mutableStateOf(0)
    }

    Box(contentAlignment = Alignment.Center) {
        Button(onClick = { count.value++ }, modifier = Modifier.align(Alignment.CenterEnd)) {
            Text("Add")
        }
//    LaunchedEffect(key1 = count) {
//        withContext(Dispatchers.IO) {
//            while (true) {
//                count.value++
//                delay(1000)
//            }
//        }
//    }
        AnimatedContent(
            targetState = count,
            transitionSpec = {
                // Compare the incoming number with the previous number.
                if (targetState.value > initialState.value) {
                    // If the target number is larger, it slides up and fades in
                    // while the initial (smaller) number slides up and fades out.
                    slideInVertically({ height -> height }) + fadeIn() with
                            slideOutVertically({ height -> -height }) + fadeOut()
                } else {
                    // If the target number is smaller, it slides down and fades in
                    // while the initial number slides down and fades out.
                    slideInVertically({ height -> -height }) + fadeIn() with
                            slideOutVertically({ height -> height }) + fadeOut()
                }.using(
                    // Disable clipping since the faded slide-in/out should
                    // be displayed out of bounds.
                    SizeTransform(clip = false)
                )
            }
        ) { targetCount ->
            Text(text = "${targetCount.value}", style = MaterialTheme.typography.h4)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ShowAnimateButton() {
    ComposestudyTheme {
        Surface(color = MaterialTheme.colors.surface, modifier = Modifier.fillMaxSize()) {
//            AnimateButton()
            SlideInAndOut()
        }
    }
}