package cn.ljpc.ui.appbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.ljpc.R
import cn.ljpc.ui.theme.ComposestudyTheme
import cn.ljpc.ui.theme.twitterColor
import java.util.Collections.copy

@Composable
fun AppBars() {
    TopAppBarsDemo()
}

@Composable
fun TopAppBarsDemo() {
    TopAppBar(
        title = { Text(text = "Home") },
        elevation = 16.dp,
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        }
    )

    Spacer(modifier = Modifier.height(16.dp))
    TopAppBar(
        title = { Text(text = "Instagram") },
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface,
        elevation = 16.dp,
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_instagram),
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_send),
                    contentDescription = null
                )
            }
        }
    )

    Spacer(modifier = Modifier.height(16.dp))

    TopAppBar(
        title = {
            Icon(
                painter = painterResource(id = R.drawable.ic_twitter),
                contentDescription = null,
                tint = twitterColor,
                modifier = Modifier.fillMaxWidth()
            )
        },
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 8.dp,
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.p6),
                contentDescription = null,
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 8.dp)
                    .size(32.dp)
                    .clip(MaterialTheme.shapes.small)
            )
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp),
                    tint = Color.Red
                )
            }
        }
    )
}

@Composable
fun BottomAppBarDemo() {
//    Spacer(modifier = Modifier.height(16.dp))
    BottomAppBar(cutoutShape = CircleShape) { //cutoutShape 剪裁形状
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
        }
        TitleText(title = "Bottom App Bar")
    }
}

@Composable
fun TitleText(modifier: Modifier = Modifier, title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.h6.copy(fontSize = 14.sp),
        modifier = modifier.padding(8.dp)
    )
}

enum class SpotifyNavType {
    HOME, SEARCH, LIBRARY
}

@Composable
fun NavigationBarDemo() {
    val spotifyNavItemState = remember {
        mutableStateOf(SpotifyNavType.HOME)
    }
    BottomNavigation(backgroundColor = MaterialTheme.colors.surface) {
        BottomNavigationItem(
            selected = spotifyNavItemState.value == SpotifyNavType.HOME,
            onClick = { spotifyNavItemState.value = SpotifyNavType.HOME },
            icon = {
                Icon(imageVector = Icons.Outlined.Home, contentDescription = null)
            },
            label = {
                Text(text = "Home")
            }
        )

        BottomNavigationItem(
            selected = spotifyNavItemState.value == SpotifyNavType.SEARCH,
            onClick = { spotifyNavItemState.value = SpotifyNavType.SEARCH },
            icon = {
                Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
            },
            label = {
                Text(text = "Seach")
            }
        )

        BottomNavigationItem(
            selected = spotifyNavItemState.value == SpotifyNavType.LIBRARY,
            onClick = { spotifyNavItemState.value = SpotifyNavType.LIBRARY },
            icon = {
                Icon(imageVector = Icons.Outlined.List, contentDescription = null)
            },
            label = {
                Text(text = "Library")
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewBottomAppBarDemo() {
    ComposestudyTheme {
        Column {
            NavigationBarDemo()
        }
    }
}