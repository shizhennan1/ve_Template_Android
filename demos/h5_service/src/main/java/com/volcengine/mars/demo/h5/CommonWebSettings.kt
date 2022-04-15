package com.volcengine.mars.demo.h5

import android.annotation.SuppressLint
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

/**
 * for
 * create at 2021/4/1
 * @author zhangzongxiang
 */
class CommonWebSettings {

    @SuppressLint("SetJavaScriptEnabled")
    internal fun apply(webView: WebView) {
        webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                Log.e(TAG, "shouldOverrideUrlLoading: ${request?.url.toString()}")
                request?.url.toString().let {
                    return !(it.startsWith("http:") || it.startsWith("https:"))
                }
            }
        }

        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
        }
    }
    companion object {

        private const val TAG = "WebSettings"
    }
}