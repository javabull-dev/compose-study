package cn.ljpc.coroutinescop

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cn.ljpc.coroutinescop.MainActivity.Companion.TAG
import cn.ljpc.coroutinescop.ui.theme.ComposestudyTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = MainViewModel()

        setContent {
            ComposestudyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainContent(mainViewModel)
                }
            }
        }
    }

    companion object {
        val TAG = MainActivity::class.simpleName
    }
}

@Composable
fun MainContent(mainViewModel: MainViewModel) {
    val coroutineScope = rememberCoroutineScope()
    var state by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(state) {
        coroutineScope.launch {
            delay(100)
            Log.d(TAG, "协程启动，${Thread.currentThread()}")
        }
    }
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = { state = !state }) {
            Text(text = "测试MainContent内部协程")
        }
        Button(onClick = { mainViewModel.invoke() }) {
            Text(text = "测试ViewModel协程")
        }
    }
}