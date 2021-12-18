package cn.ljpc.lockscreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import cn.ljpc.lockscreen.ui.theme.ComposestudyTheme
import cn.ljpc.lockscreen.viewmodel.MainViewModel
import cn.ljpc.lockscreen.viewmodel.MyAction
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class MainActivity : ComponentActivity() {

    companion object{
        val TAG = MainActivity::class.simpleName
    }

    private val mainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeEvent()
        setContent {
            ComposestudyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainContent(mainViewModel = mainViewModel)
                }
            }
        }
    }

    private fun observeEvent() {
        mainViewModel.stream().onEach {
            delay(5000)
            Log.d(TAG, "已经等待5s")
            when (it) {
                is MyAction.IntentAction -> handleIntentAction()
                else -> {}
            }
        }.launchIn(lifecycleScope)
    }

    private fun handleIntentAction() {
        val intent = Intent(this, MyService::class.java)
        startService(intent) //启动服务
    }
}

@Composable
fun MainContent(mainViewModel: MainViewModel) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(onClick = { mainViewModel.post(MyAction.IntentAction) }) {
            Text(text = "启动service")
        }
    }
}