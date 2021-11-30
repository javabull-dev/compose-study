package cn.ljpc.gmail.ui.animate

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.ljpc.gmail.ui.theme.ComposestudyTheme

@Composable
fun CrossfadeTest() {
    var currentPage by remember { mutableStateOf("A") }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = { currentPage = if (currentPage == "A") "B" else "A" },
            colors = ButtonDefaults.buttonColors(),
            modifier = Modifier.padding(20.dp)
        ) {
            Text(text = "chang screen")
        }
        Crossfade(targetState = currentPage) { screen ->
            when (screen) {
                "A" -> Text("Page A")
                "B" -> Text("Page B")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewCrossfadeTest() {
    ComposestudyTheme {
        Surface(
            color = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.onBackground,
            modifier = Modifier.fillMaxSize()
        ) {
            CrossfadeTest()
        }
    }
}