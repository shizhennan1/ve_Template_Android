package com.ss.android.init.tasks

import android.app.Application
import android.content.Context
import com.bytedance.lego.init.annotation.InitTask
import com.bytedance.lego.init.model.IInitTask
import com.bytedance.lego.init.model.InitPeriod
import com.volcengine.mars.demo.h5.H5Initializer
import com.volcengine.onekit.OneKitApp

@InitTask(
    id = "H5InitTask",
    desc = "h5初始化",
    moduleName = "h5_service",
    earliestPeriod = InitPeriod.APP_ONCREATE2SUPER,
    latestPeriod = InitPeriod.APP_ONCREATE2SUPER
)
class H5InitTask: IInitTask()  {

    override fun run() {
        val context = OneKitApp.getInstance().get(Context::class.java)
        H5Initializer.initialize(context as Application)
    }
}