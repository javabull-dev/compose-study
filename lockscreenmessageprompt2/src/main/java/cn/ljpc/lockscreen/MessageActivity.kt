package cn.ljpc.lockscreen

import android.Manifest
import android.app.KeyguardManager
import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import cn.ljpc.lockscreen.ui.theme.ComposestudyTheme
import cn.ljpc.lockscreen.viewmodel.MessageViewModel
import cn.ljpc.lockscreen.viewmodel.MyAction
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class MessageActivity : ComponentActivity() {

    companion object {
        val TAG = MessageActivity::class.simpleName
    }

    private val viewModel = MessageViewModel()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        Log.i(TAG, "onCreate:启动了消息内容的activity ")
        window.addFlags(
            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED //锁屏状态下显示
                    or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD //解锁
                    or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON //保持屏幕长亮
                    or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON //打开屏幕
        )

        //使用手机的背景
        //使用手机的背景
        val wallPaper = if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            null
        } else {
            WallpaperManager.getInstance(this).drawable
        }
        window.setBackgroundDrawable(wallPaper)
        observeEvent()

        setContent {
            ComposestudyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(onClick = { viewModel.post(MyAction.IntentAction) }) {
                            Text(text = "跳转到详情页")
                        }
                        Button(onClick = { viewModel.post(MyAction.CloseAction) }) {
                            Text(text = "关闭")
                        }
                    }
                }
            }
        }
    }

    private fun observeEvent() {
        viewModel.stream().onEach {
            when (it) {
                is MyAction.IntentAction -> handleIntentAction()
                is MyAction.CloseAction -> handleCloseAction()
                else -> {}
            }
        }.launchIn(lifecycleScope)
    }

    private fun handleCloseAction() {
        finish()
    }

    private fun handleIntentAction() {
        //先解锁系统自带锁屏服务，放在锁屏界面里面
        val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        keyguardManager.newKeyguardLock("").disableKeyguard() //解锁

        //点击进入消息对应的页面
        startActivity(Intent(this, DetailsActivity::class.java))
        finish()
    }
}