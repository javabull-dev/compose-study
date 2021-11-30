package cn.ljpc.gmail.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.ljpc.gmail.R
import cn.ljpc.gmail.ui.model.DemoDataProvider
import cn.ljpc.gmail.ui.theme.ComposestudyTheme
import cn.ljpc.gmail.ui.theme.green500
import kotlin.math.absoluteValue

@Composable
fun MainScreen() {
    val scaffoldState = rememberScaffoldState()
    val fabExpandState = remember {
        mutableStateOf(false)
    }
    val showUserDialog = remember {
        mutableStateOf(false)
    }
    Scaffold(
        drawerBackgroundColor = MaterialTheme.colors.background,
        drawerContentColor = MaterialTheme.colors.onBackground,
        scaffoldState = scaffoldState,
        content = {//主体内容
            GmailContent(
                scaffoldState = scaffoldState,
                fabExpandState = fabExpandState,
                showUserDialog = showUserDialog
            )
        },
        floatingActionButton = {//悬浮按钮
            GmailFloatingActionButton(fabExpandState.value)
        },
        drawerContent = {//侧边栏
            GmailDrawer()
        },
        bottomBar = {//底部导航栏
            BottomBar()
        }
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GmailContent(
    fabExpandState: MutableState<Boolean>,
    scaffoldState: ScaffoldState,
    showUserDialog: MutableState<Boolean>
) {

    val tweets = remember { DemoDataProvider.tweetList }
    val lazyListState = rememberLazyListState()

    val offsetY = remember { mutableStateOf(0) }
    val oldIndex = remember { mutableStateOf(0) }
    val searchOffsetY = remember { mutableStateOf(0) }

    val searchLayoutHeightPx = with(LocalDensity.current) { 70.dp.toPx() }

    // ensures that the user intents to have scroll gesture..
    val isVisibleScrolled =
        oldIndex.value != lazyListState.firstVisibleItemIndex ||
                (offsetY.value - lazyListState.firstVisibleItemScrollOffset).absoluteValue > 15

    println("${lazyListState.firstVisibleItemIndex}  ${lazyListState.firstVisibleItemScrollOffset}")

    if (isVisibleScrolled) {
        when {
            oldIndex.value > lazyListState.firstVisibleItemIndex -> {   // down
                fabExpandState.value = true
            }
            oldIndex.value < lazyListState.firstVisibleItemIndex -> {  // up
                fabExpandState.value = false
            }
            oldIndex.value == lazyListState.firstVisibleItemIndex -> {
                fabExpandState.value = offsetY.value > lazyListState.firstVisibleItemScrollOffset
            }
        }

        // for the initial search offset
        if (lazyListState.firstVisibleItemIndex == 0
            && lazyListState.firstVisibleItemScrollOffset < searchLayoutHeightPx
            && !fabExpandState.value
        ) {
            searchOffsetY.value = -lazyListState.firstVisibleItemScrollOffset
        } else if (fabExpandState.value) {
            searchOffsetY.value = 0
        } else if (!fabExpandState.value) {
            searchOffsetY.value = (-searchLayoutHeightPx).toInt()
        }

    }

    offsetY.value = lazyListState.firstVisibleItemScrollOffset
    oldIndex.value = lazyListState.firstVisibleItemIndex

    Box {
        LazyColumn(state = lazyListState) {
            item {
                Spacer(modifier = Modifier.height(72.dp))
            }
            items(tweets) {
                val visible = remember(it.id) { mutableStateOf(true) }
                AnimatedVisibility(visible = visible.value) {
                    Box(modifier = Modifier.background(green500)) {
                        GmailListActionItems(modifier = Modifier.align(Alignment.CenterEnd))
                        GmailListItem(it) {
                            //todo item click callback
                        }
                    }
                }
            }
        }
        SearchLayout(searchOffsetY.value, scaffoldState.drawerState, showUserDialog)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GmailFloatingActionButton(expandState: Boolean) {
    FloatingActionButton(
        onClick = {}, modifier = Modifier
            .padding(16.dp)
            .height(48.dp)
            .widthIn(min = 48.dp),
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.primary
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            Icon(imageVector = Icons.Outlined.Edit, contentDescription = null)
            AnimatedVisibility(visible = expandState) {
                Text(
                    text = stringResource(id = R.string.cd_create_new_email),
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }
    }
}

@Composable
fun BottomBar() {
    val selected = remember {
        mutableStateOf(0)
    }
    BottomNavigation(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground
    ) {
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

@Composable
@Preview(showBackground = true)
fun ShowMainScreen() {
    ComposestudyTheme {
        MainScreen()
    }
}

