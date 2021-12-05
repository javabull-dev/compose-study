package cn.ljpc.webview

import android.os.Bundle
import android.webkit.WebSettings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import cn.ljpc.webview.ui.theme.ComposestudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposestudyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ShowWebView()
                }
            }
        }
    }
}

@Composable
fun ShowWebView() {
    var rememberWebViewProgress by remember { mutableStateOf(-1) }

    Box {
        DefaultWebView(
            modifier = Modifier.fillMaxSize(),
            url = "file:///android_asset/open_source_licenses.html",
            onProgressChange = { progress ->
                rememberWebViewProgress = progress
            },
            initSettings = { settings ->
                settings?.apply {
                    //不支持js交互
                    javaScriptEnabled = false
                    //将图片调整到适合webView的大小
                    useWideViewPort = true
                    //缩放至屏幕的大小
                    loadWithOverviewMode = true
                    //缩放操作
                    setSupportZoom(true)
                    builtInZoomControls = true
                    displayZoomControls = true
                    //是否支持通过JS打开新窗口
                    javaScriptCanOpenWindowsAutomatically = true
                    //不加载缓存内容
                    cacheMode = WebSettings.LOAD_NO_CACHE
                }
            }, onBack = { webView ->
                if (webView?.canGoBack() == true) {
                    webView.goBack()
                } else {
//                    navController
//                    finish()
                }
            }, onReceivedError = {
                //log error
            }
        )
//        LinearProgressIndicator(
//            progress = rememberWebViewProgress * 1.0F / 100F,
//            color = Color.Red,
//            modifier = Modifier
//                .align(Alignment.Center)
//                .height(if (rememberWebViewProgress == 100) 0.dp else 5.dp),
//        )
    }
}