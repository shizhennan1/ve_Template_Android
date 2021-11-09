package com.volcengine.mars.demo.h5

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.bytedance.lynx.reader.TTReader
import com.bytedance.lynx.webview.TTWebSdk
import com.bytedance.veh5.VEH5
import com.bytedance.veh5.config.VEH5Config
import com.bytedance.veh5.debug.DebugTools
import kotlinx.android.synthetic.main.activity_h5_main.*
import java.util.regex.Pattern

class H5MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        VEH5.initialize(
            application,
            VEH5Config.Builder()
                .enableDebug(true)
                .setDeviceId("7135843788734")
                .setOfflineResourceAccessKey("d3d71ae74f5383ec33b4fd85b298f50f")
                .setOfflineResourceUrlPrefixes(getCachePrefixes())
                .setInternalCacheDirs(listOf("offline-resources"))
                .enableKernel(true)
                .setKernelInitListener(object : TTWebSdk.InitListener() {
                    override fun onDownloadFinished() {
                        super.onDownloadFinished()
                        Toast.makeText(this@H5MainActivity, "内核下载完成", Toast.LENGTH_SHORT).show()
                    }

                    override fun onSoFileUpdateFinished() {
                        Toast.makeText(this@H5MainActivity, "内核更新完成", Toast.LENGTH_SHORT).show()
                    }
                })
        )
        setContentView(R.layout.activity_h5_main)

        openDevelopActivityBtn.setOnClickListener {
            startActivity(Intent(this, H5DevelopActivity::class.java))
        }

        openExampleOnlineActivityBtn.setOnClickListener {
            VEH5.getGlobalController()?.setOfflineResourcesEnable(false)
            DebugTools.clearOfflineResourcesLoadRecord()
            startActivity(Intent(this, H5ExampleActivity::class.java))
        }

        openExampleOfflineActivityBtn.setOnClickListener {
            VEH5.getGlobalController()?.setOfflineResourcesEnable(true)
            DebugTools.clearOfflineResourcesLoadRecord()
            startActivity(Intent(this, H5ExampleActivity::class.java))
        }

        exampleTTWebViewBtn.setOnClickListener {
            startActivity(Intent(this, TTWebViewActivity::class.java))
        }

        requestPermissions()

        VEH5.updateOfflineResources(
            listOf("mars-jsbridge"), CommonUpdateListener(
                this.application, Handler(
                    Looper.getMainLooper()
                )
            )
        )
    }

    private fun getCachePrefixes() = listOf(
        Pattern.compile("https://mars-jsbridge.goofy-web.bytedance.com/"),
        Pattern.compile("https://sf6-scmcdn-tos.pstatp.com/goofy/")
    )

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
    }

}
