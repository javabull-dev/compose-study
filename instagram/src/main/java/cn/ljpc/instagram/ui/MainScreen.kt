package cn.ljpc.instagram.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.ljpc.instagram.R
import cn.ljpc.instagram.model.DemoDataProvider
import cn.ljpc.instagram.model.Item
import cn.ljpc.instagram.model.Tweet
import cn.ljpc.instagram.ui.theme.ComposestudyTheme
import cn.ljpc.instagram.ui.theme.instagramGradient

@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            AppTopBar()
        },
        content = {
            InstagramHomeContent()
        }
    )
}

@Composable
fun AppTopBar() {
    TopAppBar(
        title = {
            Text(text = "Instagram")
        },
        navigationIcon = {//前面的导航
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_instagram),
                    contentDescription = null
                )
            }
        },
        actions = {//后面的action
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Outlined.PlayArrow,
                    contentDescription = null
                )
            }
        },
        backgroundColor = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface,
    )
}

@Composable
fun InstagramHomeContent() {
    Column {
        InstagramStories()
        Divider()
        InstagramPostsList()
    }
}

@Composable
fun InstagramStories() {
    val posts = remember {
        DemoDataProvider.tweetList
    }
    LazyRow(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)) {
        items(posts) {
            InstagramStoryItem(it)
        }
    }
}

@Composable
fun InstagramStoryItem(post: Tweet) {
    val imageModifier =
        Modifier
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            .height(60.dp)
            .width(60.dp)
            .clip(CircleShape)
            .border(
                shape = CircleShape,
                border = BorderStroke(
                    width = 3.dp,
                    brush = Brush.linearGradient(
                        colors = instagramGradient,
                        start = Offset(
                            0f,
                            0f
                        ),
                        end = Offset(
                            100f,
                            100f
                        )
                    )
                )
            )
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = post.authorImageId),
            contentDescription = null,
            modifier = imageModifier,
            contentScale = ContentScale.Crop
        )
        Text(
            text = post.author,
            style = MaterialTheme.typography.caption,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun InstagramPostsList() {
    val posts = remember { DemoDataProvider.tweetList.filter { it.tweetImageId != 0 } }
    LazyColumn {
        items(posts) {
            InstagramPostItem(it)
        }
    }
}

@Composable
fun InstagramPostItem(post: Tweet) {
    Column {
        ProfileInfoSection(post = post)
        InstagramImage(imageId = post.tweetImageId)
        InstagramIconSection()
        InstagramLikesSelection(post)
        InstagramPostInfo(post)
    }
}

@Composable
fun ProfileInfoSection(post: Tweet) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = post.authorImageId),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(
            text = post.author,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .padding(8.dp)
                .weight(1f),//权重，使得其占用的空间最大
        )
        Icon(imageVector = Icons.Filled.MoreVert, contentDescription = null)
    }
}

@Composable
fun InstagramImage(imageId: Int) {
    Image(
        painter = painterResource(id = imageId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .height(height = 300.dp)
            .fillMaxWidth()
    )
}

@Composable
fun InstagramIconSection() {
    Row(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .height(30.dp)
            .fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Outlined.Person,
            contentDescription = null,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Icon(
            imageVector = Icons.Outlined.Home,
            contentDescription = null,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Icon(
            imageVector = Icons.Outlined.Send,
            contentDescription = null,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    }
}

@Composable
fun InstagramLikesSelection(post: Tweet) {
    Row(
        modifier = Modifier
            .padding(start = 8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = post.authorImageId),
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Liked by ${post.author} and ${post.likesCount} others",
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun InstagramPostInfo(post: Tweet) {
    Text(
        text = "View all ${post.commentsCount} comments",
        style = MaterialTheme.typography.caption,
        modifier = Modifier.padding(start = 8.dp, top = 4.dp),
        color = Color.Gray
    )
    Text(
        text = "${post.time} ago",
        style = MaterialTheme.typography.caption,
        modifier = Modifier.padding(start = 8.dp, top = 2.dp, bottom = 8.dp),
        color = Color.Gray
    )
}

@Preview(showBackground = true)
@Composable
fun ShowMainScreen() {
    ComposestudyTheme {
        MainScreen()
    }
}