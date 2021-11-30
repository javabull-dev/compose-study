package cn.ljpc.gmail.ui.animate

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultCameraDistance
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.scaleMatrix

/**
 * 卡片翻转
 */
@Composable
fun CardBack() {
    Card(
        modifier = Modifier
            .aspectRatio(.65f),
        backgroundColor = Color.Blue,
        border = BorderStroke(width = 16.dp, color = Color.White)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "",
                fontSize = 36.sp,
                fontWeight = FontWeight.Black,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun CardFront() {
    Card(
        contentColor = Color.Black,
        backgroundColor = Color.Red,
        border = BorderStroke(width = 16.dp, color = Color.White),
        modifier = Modifier
            .aspectRatio(.65f)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "",
                fontSize = 36.sp,
                fontWeight = FontWeight.Black,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

enum class CardState {
    Front, Back
}

@Composable
@Preview(showBackground = true)
fun ShowAnimationTest() {
    var state by remember {
        mutableStateOf(CardState.Front)
    }

    val flipTransition = updateTransition(targetState = state, label = "")
    val rotateY by flipTransition.animateFloat(
        label = "", transitionSpec = {
            tween(
                delayMillis = 50,
                durationMillis = 500,
                easing = LinearOutSlowInEasing
            )
        }
    ) {
        when (it) {
            CardState.Front -> 0f
            CardState.Back -> 180f
        }
    }

    val scale by flipTransition.animateFloat(
        transitionSpec = {
            keyframes {
                durationMillis = 500 // 500ms
                .4f at 250 with LinearEasing //在0~250ms
            }
        }, label = ""
    ) {
        when (it) {
            CardState.Front -> 1f
            CardState.Back -> 1f
        }
    }

    Surface(color = Color.Black, modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .graphicsLayer(
                    rotationY = rotateY,
                    cameraDistance = DefaultCameraDistance * 2f,
                    scaleX = scale,
                    scaleY = scale
                )
                .clickable {
                    state = if (state == CardState.Front) {
                        CardState.Back
                    } else {
                        CardState.Front
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            if (rotateY <= 90f) {
                CardFront()
            } else {
                CardBack()
            }
        }
    }
}