package cn.ljpc.ui.tabslayout

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cn.ljpc.ui.list.GridListView
import cn.ljpc.ui.list.VerticalListView
import cn.ljpc.ui.theme.ComposestudyTheme

private enum class DemoTabs(val value: String) {
    APPLE("Apple"),
    GOOGLE("Google"),
    AMAZON("Amazon")
}

@Composable
fun TabLayout() {
    val tabsNames = remember {
        DemoTabs.values().map { it.value }
    }
    val selectedIndex = remember {
        mutableStateOf(DemoTabs.APPLE.ordinal)
    }
    val icons = listOf(Icons.Default.Info, Icons.Default.Person, Icons.Default.ShoppingCart)
    Column {
        TabRow(selectedTabIndex = selectedIndex.value) {
            tabsNames.forEachIndexed { index, title ->
                Tab(
                    selected = index == selectedIndex.value,
                    onClick = {
                        when (title) {
                            DemoTabs.APPLE.value -> {
                                selectedIndex.value = DemoTabs.APPLE.ordinal
                            }
                            DemoTabs.GOOGLE.value -> {
                                selectedIndex.value = DemoTabs.GOOGLE.ordinal
                            }
                            DemoTabs.AMAZON.value -> {
                                selectedIndex.value = DemoTabs.AMAZON.ordinal
                            }
                        }
                    },
                    text = { Text(text = title) },
                    icon = {
                        Icon(imageVector = icons[index], contentDescription = null)
                    }
                )
            }
        }
        Surface(modifier = Modifier.weight(0.5f)) {
            when (selectedIndex.value) {
                DemoTabs.APPLE.ordinal -> {
                    VerticalListView()
                }
                DemoTabs.GOOGLE.ordinal -> {
                    GridListView()
                }
                DemoTabs.AMAZON.ordinal -> {
                    VerticalListView()
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewTabLayout() {
    ComposestudyTheme {
        TabLayout()
    }
}