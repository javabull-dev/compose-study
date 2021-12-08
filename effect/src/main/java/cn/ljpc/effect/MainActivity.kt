package cn.ljpc.effect

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import cn.ljpc.effect.ui.MainContent2
import cn.ljpc.effect.ui.theme.ComposestudyTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposestudyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.surface
                ) {
//                    SideEffect {
//                        Log.d("", "Surface --- SideEffect终于被调用了")
//                    }
                    MainContent2()
                }
            }
        }
    }
}

