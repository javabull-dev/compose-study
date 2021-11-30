package cn.ljpc.gmail.ui.model

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController

/**
 * 定义我们自己的LocalProvider
 */
val LocalNavController = compositionLocalOf<NavHostController> {
    error("no LocalNavController")
}