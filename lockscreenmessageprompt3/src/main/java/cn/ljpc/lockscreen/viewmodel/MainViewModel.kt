package cn.ljpc.lockscreen.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel<Action>() {

    companion object {
        val TAG = MainViewModel::class.simpleName
    }

    fun sendNotificationLater() {
        Log.d(TAG, "5s后发送通知")
        viewModelScope.launch {
            delay(5000)
            Log.d(TAG, "发送一条通知")
            post(MyAction.NotificationAction)
        }
    }
}