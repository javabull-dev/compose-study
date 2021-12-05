package cn.ljpc.webview

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class DefaultWebViewClient(
    val onProgressChange: (progress: Int) -> Unit = {},
    val onReceivedError: (error: WebResourceError?) -> Unit = {}
) : WebViewClient() {
    override fun onPageStarted(
        view: WebView?, url: String?,
        favicon: Bitmap?
    ) {
        super.onPageStarted(view, url, favicon)
        onProgressChange(-1)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        onProgressChange(100)
    }

    override fun shouldOverrideUrlLoading(
        view: WebView?,
        request: WebResourceRequest?
    ): Boolean {
        if (null == request?.url) return false
        val showOverrideUrl = request.url.toString()
        try {
            if (!showOverrideUrl.startsWith("http://")
                && !showOverrideUrl.startsWith("https://")
            ) {
                //处理非http和https开头的链接地址
                Intent(Intent.ACTION_VIEW, Uri.parse(showOverrideUrl)).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    view?.context?.applicationContext?.startActivity(this)
                }
                return true
            }
        } catch (e: Exception) {
            //没有安装和找到能打开(「xxxx://openlink.cc....」、「weixin://xxxxx」等)协议的应用
            return true
        }
        return super.shouldOverrideUrlLoading(view, request)
    }

    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        super.onReceivedError(view, request, error)
        onReceivedError(error)
    }
}