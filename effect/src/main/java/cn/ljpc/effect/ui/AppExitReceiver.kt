package cn.ljpc.effect.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import java.util.*

/**
 * 接收系统语言更换的通知，杀死我们的app
 */
class AppExitReceiver : BroadcastReceiver() {

    companion object {
        val TAG = AppExitReceiver::class.simpleName
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == null)
            return
        if (intent.action.equals(Intent.ACTION_LOCALE_CHANGED)) {
            Log.d(TAG, "android系统语言更改了，我们需要重新启app了，启动的办法就是杀死我们的app")
            Log.d(TAG, "context: 的类型=$context")
            //更改locale
            context?.resources?.configuration?.locale = Locale.getDefault()
        }
    }
}