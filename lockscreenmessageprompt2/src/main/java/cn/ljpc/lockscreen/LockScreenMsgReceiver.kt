package cn.ljpc.lockscreen

import android.app.KeyguardManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import cn.ljpc.lockscreen.ConstValue.INTENT_ACTION


class LockScreenMsgReceiver : BroadcastReceiver() {

    companion object {
        val TAG = LockScreenMsgReceiver::class.simpleName
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i(TAG, "onReceive:收到了锁屏消息 ")
        if (intent!!.action == INTENT_ACTION) {
            //管理锁屏的一个服务
            val km = context!!.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
            if (km.inKeyguardRestrictedInputMode()) {
                Log.i(TAG, "onReceive:锁屏了 ")
                //判断是否锁屏
                val alarmIntent = Intent(context, MessageActivity::class.java)
                //在广播中启动Activity的context可能不是Activity对象，所以需要添加NEW_TASK的标志，否则启动时可能会报错。
                alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                if (Build.VERSION.SDK_INT >= 26) {
                    alarmIntent.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP)
                }
                context.startActivity(alarmIntent) //启动显示锁屏消息的activity
            } else {
                Log.i(TAG, "onReceive:屏幕亮着的 ")
            }
        }
    }
}