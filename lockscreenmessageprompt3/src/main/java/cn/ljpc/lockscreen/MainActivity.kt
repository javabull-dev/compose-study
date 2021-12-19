package cn.ljpc.lockscreen

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.Person
import androidx.lifecycle.lifecycleScope
import cn.ljpc.lockscreen.ui.theme.ComposestudyTheme
import cn.ljpc.lockscreen.viewmodel.MainViewModel
import cn.ljpc.lockscreen.viewmodel.MainViewModel.Companion.TAG
import cn.ljpc.lockscreen.viewmodel.MyAction
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import me.leolin.shortcutbadger.ShortcutBadger
import java.util.*

class MainActivity : ComponentActivity() {

    companion object {
        val TAG = MainActivity::class.simpleName
    }

    private val mainViewModel = MainViewModel()

    private var channelId: String? = null

    private var notificationId = 0

    private var groupId = "group"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeEvent()
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

    override fun onResume() {
        super.onResume()
        val str = intent.getStringExtra("test")
        Log.d(TAG, "#onResume str =${str}")
    }

    fun observeEvent() {
        mainViewModel.stream()
            .onEach {
                when (it) {
                    is MyAction.NotificationAction -> handleNotification()
                    else -> {}
                }
            }.launchIn(lifecycleScope)
    }

    private fun createChannel() {
        channelId = UUID.randomUUID().toString()
        //创建channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "my channel"
            val descriptionText = "通道"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    @SuppressLint("PrivateResource", "UnspecifiedImmutableFlag")
    private fun handleNotification() {
        if (channelId == null) {
            createChannel()
        }

//        val intent = Intent(Intent.ACTION_MAIN).apply {
//            //作为launch app
//            addCategory(Intent.CATEGORY_LAUNCHER)
//            setClass(this@MainActivity, MainActivity::class.java)
//            // 不创建 Activity
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
//        }

        val intent = Intent(this, MainActivity::class.java).apply {
            //清除底部的activity，并且以单例的方式启动改Activity
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        //尝试传递参数
        intent.putExtra("test", "test")

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val notification1 = NotificationCompat.Builder(this, channelId!!)
            .setSmallIcon(androidx.core.R.drawable.notification_bg_low_pressed)
            .setStyle(
                NotificationCompat.MessagingStyle(Person.Builder().setName("jack").build())
                    .addMessage("hello", Date().time, Person.Builder().setName("jack").build())
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setGroup(groupId)
            .setContentIntent(pendingIntent)
//            .setNumber(10) //设置badge
            .build()

        Log.d(TAG, "id=$notificationId")
        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, notification1)
        }
        notificationId++

        //这种方式并不使用所有的手机
        val badgeCount = 20
        ShortcutBadger.applyCount(this, badgeCount)
    }
}

@Composable
fun MainContent(mainViewModel: MainViewModel) {
    val context = LocalContext.current
    Log.d(TAG, "当前的Activity# $context")
    fun openNotification() {
        val localIntent = Intent().apply {
            //判断API，跳转到应用通知管理页面
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//8.0及以上
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                action = "android.settings.APPLICATION_DETAILS_SETTINGS"
                data = Uri.fromParts("package", context.packageName, null)
            } else {
                action = "android.settings.APP_NOTIFICATION_SETTINGS"
                putExtra("app_package", context.packageName)
                putExtra("app_uid", context.applicationInfo.uid)
            }
        }
        context.startActivity(localIntent)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = {
            mainViewModel.sendNotificationLater()
        }) {
            Text(text = "5s发送通知")
        }
        Text(text = "$context")
        Button(onClick = { openNotification() }) {
            Text(text = "去打开通知")
        }
        Text(text = "是否一打开通知？${NotificationManagerCompat.from(context).areNotificationsEnabled()}")
    }
}