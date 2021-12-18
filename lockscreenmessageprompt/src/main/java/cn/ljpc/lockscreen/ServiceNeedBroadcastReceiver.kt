package cn.ljpc.lockscreen

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ServiceNeedBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null) {
            //手机震动
            ToolUtils.playVibrate(context, true)
            //播放系统默认闹钟铃声
            ToolUtils.defaultAlarmMediaPlayer(context)
        }
    }
}