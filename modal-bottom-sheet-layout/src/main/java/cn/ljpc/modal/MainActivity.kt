package cn.ljpc.modal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cn.ljpc.modal.ui.theme.ComposestudyTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposestudyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainContent()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent() {
    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetState = state,
        sheetContent = {
            Column {
                ListItem(text = { Text("选择分享到哪里吧~") })

                ListItem(text = { Text("github") }, icon = {
                    Surface(
                        shape = CircleShape,
                        color = Color(0xFF181717)
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowUp,
                            null,
                            tint = Color.White,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }, modifier = Modifier.clickable { })

                ListItem(text = { Text("微信") }, icon = {
                    Surface(
                        shape = CircleShape,
                        color = Color(0xFF07C160)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            null,
                            tint = Color.White,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }, modifier = Modifier.clickable { })
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                scope.launch {
                    state.animateTo(
                        ModalBottomSheetValue.Expanded,
                        tween(1000)
                    )
                }
            }) {
                Text("点我展开")
            }
        }

        BackHandler(
            enabled = (state.currentValue == ModalBottomSheetValue.HalfExpanded
                    || state.currentValue == ModalBottomSheetValue.Expanded),
            onBack = {
                scope.launch {
                    state.animateTo(ModalBottomSheetValue.Hidden, tween(1000))
                }
            }
        )
    }
}