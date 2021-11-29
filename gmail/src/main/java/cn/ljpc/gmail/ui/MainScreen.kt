package cn.ljpc.gmail.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen() {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        drawerBackgroundColor = MaterialTheme.colors.background,
        drawerContentColor = MaterialTheme.colors.onBackground,
        scaffoldState = scaffoldState,
        content = {//主体内容

        },
        floatingActionButton = {//悬浮按钮

        },
        drawerContent = {//侧边栏

        },
        bottomBar = {//底部导航栏
            BottomBar()
        }
    )
}

@Composable
fun HomeMessageItem() {

}

@Composable
fun BottomBar() {
    val selected = remember {
        mutableStateOf(0)
    }
    BottomNavigation(modifier = Modifier.fillMaxWidth()) {
        BottomNavigationItem(
            selected = selected.value == 0,
            onClick = {
                selected.value = 0
            },
            icon = {
                IconWithBadge(badge = 1, icon = Icons.Outlined.Email)
            },
            label = {
                Text(text = "Mail")
            }
        )
        BottomNavigationItem(
            selected = selected.value == 1,
            onClick = {
                selected.value = 1
            },
            icon = {
                IconWithBadge(badge = 0, icon = Icons.Outlined.Call)
            },
            label = {
                Text(text = "Meet")
            }
        )
    }
}

@Composable
fun IconWithBadge(badge: Int, icon: ImageVector, modifier: Modifier = Modifier) {
    Box(modifier = Modifier.size(36.dp)) {
        Icon(
            imageVector = icon,
            modifier = modifier.align(
                Alignment.BottomCenter
            ),
            contentDescription = null
        )

        if (badge != 0) {
            Text(
                text = "$badge",
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                color = MaterialTheme.colors.surface,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .clip(CircleShape)
                    .background(Color.Red)
                    .align(Alignment.TopEnd)
                    .size(16.dp)
            )
        }
    }
}

