package cn.ljpc.lockscreen

import android.app.Service
import android.content.Intent
import android.os.IBinder

class TaskService : Service() {

    /**
     * 通过bindService()绑定到服务的客户端
     */
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    /**
     * 调用startService()启动服务时的回调
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //在Service服务类中发送广播消息给Activity活动界面
        val intentBroadcastReceiver = Intent()
        //设置意图过虑器Action（用来区分广播来源，相当于是广播的身份证）
        intentBroadcastReceiver.setAction(ACTION_SERVICE_NEED)
        //添加NEW_TASK标志位（必须加这个，否则不能在锁屏下实现消息提醒）
        intentBroadcastReceiver.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        //发送无序广播
        sendBroadcast(intentBroadcastReceiver)

        return super.onStartCommand(intent, flags, startId)
    }
}