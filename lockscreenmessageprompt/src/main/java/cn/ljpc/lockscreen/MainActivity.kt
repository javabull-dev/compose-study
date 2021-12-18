package cn.ljpc.lockscreen

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cn.ljpc.lockscreen.ui.theme.ComposestudyTheme

//声明一个操作常量字符串
const val ACTION_SERVICE_NEED = "action.ServiceNeed"

class MainActivity : ComponentActivity() {

    companion object {
        val TAG = MainActivity::class.simpleName
    }

    //声明一个内部广播实例
    private lateinit var broadcastReceiver: ServiceNeedBroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(
            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED //锁屏状态下显示
                    or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD //解锁
                    or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON //保持屏幕长亮
                    or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON//打开屏幕
        )
        val filter = IntentFilter()
        //给意图过虑器增加一个Action（用来区分广播来源，相当于是广播的身份证）
        filter.addAction(ACTION_SERVICE_NEED)
        broadcastReceiver = ServiceNeedBroadcastReceiver()
        registerReceiver(broadcastReceiver, filter)
        setContent {
            ComposestudyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var state by remember {
                        mutableStateOf(false)
                    }
                    Row(
                        modifier = Modifier.padding(10.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(onClick = {
                            state = !state
                            if (state) {
                                start()
                            } else {
                                stop()
                            }
                        }) {
                            if (!state) {
                                Text("启动服务")
                            } else {
                                Text("关闭服务")
                            }
                        }
                    }
                }
            }
        }
    }

    private fun start() {
        Log.d(TAG, "已启动服务。。。。")
        val intent = Intent()
        intent.setClass(this, TaskService::class.java)
        startService(intent)
    }

    private fun stop() {
        Log.d(TAG, "已停止服务。。。。")
        val intent = Intent()
        intent.setClass(this, TaskService::class.java)
        stopService(intent)
    }
}