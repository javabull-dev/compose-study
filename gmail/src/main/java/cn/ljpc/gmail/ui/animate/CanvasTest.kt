package cn.ljpc.gmail.ui.animate

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import cn.ljpc.gmail.ui.theme.ComposestudyTheme
import java.util.Collections.rotate

@Composable
fun CanvasTest() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        drawLine(
            start = Offset(x = canvasWidth, y = 0f),
            end = Offset(x = 0f, y = canvasHeight),
            color = Color.Blue,
            strokeWidth = 5f,//改变线条宽度
        )

        drawCircle(
            color = Color.Blue,
            center = Offset(x = canvasWidth / 2, y = canvasHeight / 2),
            radius = size.minDimension / 4
        )
    }
}

//@Composable
//fun CanvasTest2(){
//    withTransform({
//        translate(left = canvasWidth / 5F)
//        rotate(degrees = 45F)
//    }) {
//        drawRect(
//            color = Color.Gray,
//            topLeft = Offset(x = canvasWidth / 3F, y = canvasHeight / 3F),
//            size = canvasSize / 3F
//        )
//    }
//}

@Composable
@Preview(showBackground = true)
fun ShowCanvasTest() {
    ComposestudyTheme {
        CanvasTest()
    }
}