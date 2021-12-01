package com.volcengine.mars.app

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.bytedance.lego.init.model.InitPeriod.MAIN_ONCREATE2SUPER
import com.bytedance.lego.init.model.InitPeriod.MAIN_ONRESUME2SUPER
import com.bytedance.lego.init.model.InitPeriod.MAIN_SUPER2ONCREATEEND
import com.bytedance.lego.init.model.InitPeriod.MAIN_SUPER2ONRESUMEEND
import com.volcengine.mars.activity.BaseActivity
import com.volcengine.mars.utils.AppUtils

class MarsActivity : BaseActivity() {
    companion object {

        const val TAG: String = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        MPLaunch.onPeriod(this.application, MAIN_ONCREATE2SUPER)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_layout)
        MPLaunch.onPeriod(this.application, MAIN_SUPER2ONCREATEEND)
    }

    override fun onResume() {
        MPLaunch.onPeriod(this.application, MAIN_ONRESUME2SUPER)
        super.onResume()
        MPLaunch.onPeriod(this.application, MAIN_SUPER2ONRESUMEEND)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.developer_helper -> {
                val comp = ComponentName(
                    this,
                    "com.volcengine.dev_tool.DeveloperActivity"
                )
                val intent = Intent()
                intent.component = comp
                if (getPackageManager().resolveActivity(intent, 0) != null) {
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "com.volcengine.dev_tool.DeveloperActivity只在debug包存在！", Toast.LENGTH_LONG)
                }
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }

        }
    }
}