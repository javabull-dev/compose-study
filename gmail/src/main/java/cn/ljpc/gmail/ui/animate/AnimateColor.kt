package cn.ljpc.gmail.ui.animate

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import cn.ljpc.gmail.ui.theme.ComposestudyTheme

@Composable
fun AnimateColor() {
    var changeState by remember {
        mutableStateOf(false)
    }
    val animatedColor =
        animateColorAsState(
            targetValue = if (changeState) Color.Green else Color.Red,
            animationSpec = tween<Color>(
                durationMillis = 900,
                delayMillis = 200,
                easing = LinearOutSlowInEasing
            )
        )
    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = { changeState = !changeState }) {
            Text(text = "改变颜色")
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = animatedColor.value)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ShowAnimateColor() {
    ComposestudyTheme {
        AnimateColor()
    }
}