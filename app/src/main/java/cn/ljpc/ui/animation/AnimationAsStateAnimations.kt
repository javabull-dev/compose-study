package cn.ljpc.ui.animation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SimpleColorStateAnimation() {
    /**
     * 按钮颜色变化
     */
    val enabled = remember {
        mutableStateOf(false)
    }
    val animateColor = animateColorAsState(
        targetValue = if (enabled.value) MaterialTheme.colors.primary else MaterialTheme.colors.secondary
    )
    val buttonColors = ButtonDefaults.buttonColors(
        backgroundColor = animateColor.value
    )

    Button(
        onClick = { enabled.value = !enabled.value }, colors = buttonColors,
        modifier = Modifier
            .padding(16.dp)
            .height(40.dp)
    ) {
        Text(text = "click")
    }
}

@Composable
fun SimpleDpStateAnimations() {
    /**
     * button的大小发生变化
     */
    var enabled by remember { mutableStateOf(true) }
    val animatedColorState = animateColorAsState(
        targetValue = if (enabled) MaterialTheme.colors.primary else MaterialTheme.colors.secondary
    )
    val buttonColors = ButtonDefaults.buttonColors(
        backgroundColor = animatedColorState.value
    )

    val animateHeightState = animateDpAsState(targetValue = if (enabled) 40.dp else 60.dp)
    val animateWidthState = animateDpAsState(targetValue = if (enabled) 80.dp else 100.dp)

    Button(
        onClick = { enabled = !enabled }, colors = buttonColors,
        modifier = Modifier
            .padding(16.dp)
            .height(animateHeightState.value)
            .width(animateWidthState.value)
    ) {
        Text(text = "click")
    }
}

@Composable
fun SimpleFloatStateAnimation() {
    /**
     * float值，
     * 此处为改变透明度
     */
    var enabled by remember { mutableStateOf(true) }
    val animatedFloatState = animateFloatAsState(targetValue = if (enabled) 1f else 0.5f)
    Button(
        onClick = { enabled = !enabled },
        modifier = Modifier
            .padding(16.dp)
            .alpha(animatedFloatState.value)
    ) {
        Text("Opacity change")
    }
}

@Composable
@Preview(showBackground = true)
fun ShowSimpleColorStateAnimation() {
    Column {
        SimpleColorStateAnimation()
        Divider()
        SimpleDpStateAnimations()
    }
}