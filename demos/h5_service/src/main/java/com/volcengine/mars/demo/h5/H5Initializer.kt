package com.volcengine.mars.demo.h5

import android.app.Application
import android.widget.Toast
import com.bytedance.lynx.webview.TTWebSdk
import com.bytedance.veh5.VEH5
import com.bytedance.veh5.config.VEH5Config
import java.util.regex.Pattern

/**
 * for
 * create at 2021/11/10
 * @author zhangzongxiang
 */
object H5Initializer {

    fun initialize(application: Application) {
        VEH5.initialize(
            application,
            VEH5Config.Builder()
                .enableDebug(true)
                .setDeviceId("7135843788734")
                .setOfflineResourceAccessKey("f8cbcdad44a36a88209f465172f7ce68")
                .setOfflineResourceUrlPrefixes(getCachePrefixes())
                .setInternalCacheDirs(listOf("offline-resources"))
                .enableKernel(true)
                .setKernelInitListener(object : TTWebSdk.InitListener() {
                    override fun onDownloadFinished() {
                        super.onDownloadFinished()
                        Toast.makeText(application, "内核下载完成", Toast.LENGTH_SHORT).show()
                    }

                    override fun onSoFileUpdateFinished() {
                        Toast.makeText(application, "内核更新完成", Toast.LENGTH_SHORT).show()
                    }
                })
        )
    }

    private fun getCachePrefixes() = listOf(
        Pattern.compile("https://mars-jsbridge.goofy-web.bytedance.com/"),
        Pattern.compile("https://sf6-scmcdn-tos.pstatp.com/goofy/")
    )
}