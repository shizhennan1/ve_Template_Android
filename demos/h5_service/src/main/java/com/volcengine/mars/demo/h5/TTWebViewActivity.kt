package com.volcengine.mars.demo.h5

import com.bytedance.veh5.VEH5
import com.bytedance.veh5.WebViewCreateProvider
import com.bytedance.webx.core.webview.WebViewContainer
import com.volcengine.mars.demo.h5.base.BaseH5Activity

class TTWebViewActivity : BaseH5Activity() {


    override fun createWebViewContainer(): WebViewContainer {
        return VEH5.obtainWebViewContainer(this, WebViewCreateProvider())!!
    }

    override fun applySettings() {
        super.applySettings()

        webView.loadUrl("https://www.ip138.com/useragent/")
    }
}