package com.volcengine.mars.demo.h5.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.bytedance.veh5.VEH5
import com.bytedance.webx.core.webview.WebViewContainer
import com.volcengine.mars.demo.h5.CommonWebSettings
import com.volcengine.mars.demo.h5.R
import kotlinx.android.synthetic.main.activity_h5_base.*

/**
 * for
 * create at 2021/4/25
 * @author zhangzongxiang
 */
abstract class BaseH5Activity : AppCompatActivity() {

    protected lateinit var webView: WebViewContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_h5_base)

        buildUi()

        applySettings()
    }

    protected open fun buildUi() {

        webView = createWebViewContainer()

        val layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT)

        webViewContainer.addView(webView, layoutParams)
    }

    abstract fun createWebViewContainer(): WebViewContainer

    @SuppressLint("SetJavaScriptEnabled")
    protected open fun applySettings() {

        CommonWebSettings().apply(webView)

    }

    override fun onDestroy() {
        super.onDestroy()
        VEH5.onWebViewDestroy(webView)
    }

}