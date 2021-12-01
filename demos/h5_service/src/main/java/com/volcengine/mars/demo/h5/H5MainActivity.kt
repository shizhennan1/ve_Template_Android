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

        setContentView(R.layout.activity_h5_main)

        updateOfflineResourcesBtn.setOnClickListener {
            VEH5.updateOfflineResources(
                listOf("mars-jsbridge"), CommonUpdateListener(
                    this.application, Handler(
                        Looper.getMainLooper()
                    )
                )
            )
        }

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
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
    }

}
