package cn.ljpc.coroutinescop

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    companion object {
        val TAG = MainViewModel::class.simpleName
    }

    private val coroutineName = CoroutineName("my-new-coroutine-context")

    private val coroutineScope = CoroutineScope(coroutineName)

    fun invoke() {
        Thread {
            Log.d(TAG, "线程启动，${Thread.currentThread()}")
            //launch的协程和Dispatcher有关系
            coroutineScope.launch(context = Dispatchers.IO) {
                delay(100)
                Log.d(TAG, "协程结束，${Thread.currentThread()}")
            }
            Log.d(TAG, "线程结束，${Thread.currentThread()}")
        }.start()
    }
}