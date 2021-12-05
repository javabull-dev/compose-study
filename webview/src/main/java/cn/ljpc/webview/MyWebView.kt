package cn.ljpc.webview

import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.launch

@Composable
fun DefaultWebView(
    modifier: Modifier = Modifier,
    url: String,
    onBack: (webView: WebView?) -> Unit,
    onProgressChange: (progress: Int) -> Unit = {},
    initSettings: (webSettings: WebSettings?) -> Unit = {},
    onReceivedError: (error: WebResourceError?) -> Unit = {}
) {
    val webViewChromeClient = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            onProgressChange(newProgress)
            super.onProgressChanged(view, newProgress)
        }
    }
    val webViewClient =
        DefaultWebViewClient(onProgressChange = onProgressChange, onReceivedError = onReceivedError)
    var webView: WebView? = null
    val coroutineScope = rememberCoroutineScope()
    AndroidView(modifier = modifier, factory = { ctx ->
        WebView(ctx).apply {
            this.webViewClient = webViewClient
            this.webChromeClient = webViewChromeClient
            initSettings(this.settings)
            webView = this
            loadUrl(url)
        }
    })
    BackHandler {
        coroutineScope.launch {
            onBack(webView)
        }
    }
}