package cn.ljpc.lockscreen

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*


/**
 * 模拟推送，在退出APP后的一段时间发送消息
 */
class MyService : Service() {

    companion object {
        val TAG = MyService::class.simpleName
    }

    private val job = Job()

    private val coroutineScope =
        CoroutineScope(job + CoroutineName(TAG.toString() + Dispatchers.Default))

    override fun onBind(intent: Intent?): IBinder? {
        Log.i(TAG, "onBind")
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand --- start send message")
        coroutineScope.launch {
            delay(5_000)
            val myIntent = Intent().apply {
                action = "action.send.lock.message"
                component = ComponentName(
                    "cn.ljpc.lockscreen",
                    LockScreenMsgReceiver::class.qualifiedName.toString()
                )
            }
            sendBroadcast(myIntent)
        }
        return super.onStartCommand(intent, flags, startId)
    }
}